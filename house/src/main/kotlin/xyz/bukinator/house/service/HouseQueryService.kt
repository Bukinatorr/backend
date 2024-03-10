package xyz.bukinator.house.service

import jakarta.persistence.criteria.CriteriaBuilder
import jakarta.persistence.criteria.Expression
import jakarta.persistence.criteria.Path
import jakarta.persistence.criteria.Predicate
import jakarta.persistence.criteria.Root
import org.springframework.data.domain.PageRequest
import org.springframework.data.jpa.domain.Specification
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import xyz.bukinator.house.model.House
import xyz.bukinator.house.model.embeddable.Area
import xyz.bukinator.house.model.embeddable.Floor
import xyz.bukinator.house.model.embeddable.Location
import xyz.bukinator.house.model.embeddable.Price
import xyz.bukinator.house.repository.HouseRepository
import xyz.bukinator.house.service.dto.HouseQueryCriteria


@Service
class HouseQueryService(
    private val houseRepository: HouseRepository,
) {

    @Transactional(readOnly = true)
    fun list(criteria: HouseQueryCriteria, page: Int, size: Int): List<House> {
        return houseRepository.findAll(
            buildSpecification(criteria = criteria),
            PageRequest.of(page, size)
        ).content
    }

    @Transactional(readOnly = true)
    fun list(specification: Specification<House>, pageable: PageRequest): List<House> {
        return list(specification, pageable)
    }

    internal fun buildSpecification(criteria: HouseQueryCriteria): Specification<House> {
        return Specification<House> { root, _, criteriaBuilder ->
            val predicates = mutableListOf<Predicate>()

            criteria.salesType?.let {
                val salesType = root.get<String>("salesType")
                predicates.add(criteriaBuilder.equal(salesType, it))
            }

            val isRentAndDepositConvertible = criteria.isRentAndDepositConvertible
            if (isRentAndDepositConvertible) {
                buildConvertibleRentAndDepositPredicate(criteria, root, criteriaBuilder, predicates)
            } else {
                buildRentAndDepositPredicate(criteria, root, criteriaBuilder, predicates)
            }

            criteria.roomType?.let {
                // TODO: Fix to use enum
                val roomType = root.get<String>("roomType")
                predicates.add(criteriaBuilder.equal(roomType, it))
            }

            criteria.houseType?.let {
                // TODO: Fix to use enum
                val houseType = root.get<String>("houseType")
                predicates.add(criteriaBuilder.equal(houseType, it))
            }

            criteria.approveDateAfter?.let {
                predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("approveDate"), it))
            }

            criteria.minFloor?.let {
                predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("floor"), it))
            }

            criteria.minFloor?.let {
                predicates.add(criteriaBuilder.equal(root.get<Floor>("floor"), it))
            }

            criteria.screenLocation?.let {
                predicates.add(
                    criteriaBuilder.and(
                        criteriaBuilder.greaterThanOrEqualTo(root.get<Location>("location").get<Double>("lat"), it.northWest.lat),
                        criteriaBuilder.lessThanOrEqualTo(root.get<Location>("location").get<Double>("lat"), it.southEast.lat),
                        criteriaBuilder.greaterThanOrEqualTo(root.get<Location>("location").get<Double>("lng"), it.northWest.lng),
                        criteriaBuilder.lessThanOrEqualTo(root.get<Location>("location").get<Double>("lng"), it.southEast.lng)
                    )
                )
            }

            criteria.parkingCount?.let {
                predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("parkingCount"), it))
            }

            criteria.area?.let {
                val area = root.get<Area>("area")
                val areaToUse = area.get<Double>("areaContract") ?: area.get<Double>("areaSupply") ?: area.get<Double>("areaIndividual")
                    // 면적 정보를 사용할 수 없는 경우에는 무시한다
                    ?: return@let
                predicates.add(criteriaBuilder.between(areaToUse, it.min, it.max))
            }

            if (predicates.isEmpty()) {
                throw IllegalArgumentException("No valid criteria provided")
            }

            criteriaBuilder.and(*predicates.toTypedArray())
        }
    }

    private fun buildRentAndDepositPredicate(criteria: HouseQueryCriteria, root: Root<House>, criteriaBuilder: CriteriaBuilder, predicates: MutableList<Predicate>) {
        criteria.deposit?.let {
            val price = root.get<Price>("price")
            val deposit = price.get<Int>("priceDeposit")
            predicates.add(criteriaBuilder.between(deposit, it.min, it.max))
        }

        criteria.rent?.takeIf {
            // TODO: Fix to use enum
            criteria.salesType == "월세" || criteria.salesType == null
        }?.let {
            val price = root.get<Price>("price")
            val rent = if (criteria.isRentIncludesManage) {
                criteriaBuilder.sum(
                    price.get<Int>("priceRent"),
                    criteriaBuilder.prod(price.get<Double>("priceManage"), 10000)
                ).`as`(Int::class.java)
            } else {
                price.get<Int>("priceRent")
            }
            predicates.add(criteriaBuilder.between(rent, it.min, it.max))
        }
    }

    private fun buildConvertibleRentAndDepositPredicate(criteria: HouseQueryCriteria, root: Root<House>, criteriaBuilder: CriteriaBuilder, predicates: MutableList<Predicate>) {
        fun calculateScore(rent: Expression<Int>, deposit: Path<Int>): Expression<Int> {
            return criteriaBuilder.sum(
                rent,
                criteriaBuilder.prod(criteriaBuilder.quot(deposit, 1000), 5)
            ).`as`(Int::class.java)
        }

        val price = root.get<Price>("price")

        if (criteria.deposit == null || criteria.rent == null) return

        val deposit = price.get<Int>("priceDeposit")
        val rent = price.get<Int>("priceRent")

        val calculatedScore = if (criteria.isRentIncludesManage) {
            val rentAndManage = criteriaBuilder.sum(
                rent,
                criteriaBuilder.prod(price.get<Double>("priceManage"), 10000)
            ).`as`(Int::class.java)

            calculateScore(rentAndManage, deposit)
        } else {
            calculateScore(rent, deposit)
        }

        val maxScore = criteria.rent.max.plus((criteria.deposit.max / UNIT_DEPOSIT * DEFAULT_RENT_PER_UNIT_DEPOSIT))
        val minScore = criteria.rent.min.plus((criteria.deposit.min / UNIT_DEPOSIT * DEFAULT_RENT_PER_UNIT_DEPOSIT))

        predicates.add(criteriaBuilder.between(calculatedScore, minScore, maxScore))
    }

    companion object {
        private const val DEFAULT_RENT_PER_UNIT_DEPOSIT = 50
        private const val UNIT_DEPOSIT = 1000
    }
}
