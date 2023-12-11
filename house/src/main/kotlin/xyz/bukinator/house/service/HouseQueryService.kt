package xyz.bukinator.house.service

import jakarta.persistence.criteria.Predicate
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.jpa.domain.Specification
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import xyz.bukinator.house.model.House
import xyz.bukinator.house.model.embeddable.Floor
import xyz.bukinator.house.model.embeddable.LatLng
import xyz.bukinator.house.repository.HouseRepository
import xyz.bukinator.house.service.dto.HouseQueryCriteria

@Service
class HouseQueryService(
    private val houseRepository: HouseRepository,
) {

    @Transactional(readOnly = true)
    fun list(criteria: HouseQueryCriteria, page: Int, size: Int): List<House> {
        val pageable = PageRequest.of(page, size)

        return houseRepository.findAll(buildSpecification(criteria), pageable).content
    }

    private fun buildSpecification(criteria: HouseQueryCriteria): Specification<House> {
        return Specification { root, _, criteriaBuilder ->
            val predicates = mutableListOf<Predicate>()

            criteria.salesType?.let {
                // TODO: Fix to use enum
                val salesType = root.get<String>("salesType")
                predicates.add(criteriaBuilder.equal(salesType, it))
            }

            criteria.deposit?.let {
                val deposit = root.get<Int>("deposit")
                predicates.add(criteriaBuilder.between(deposit, it.min, it.max))
            }

            criteria.rent?.takeIf {
                // TODO: Fix to use enum
                criteria.salesType == "월세" || criteria.salesType == null
            }?.let {
                val rent = root.get<Int>("rent")
                predicates.add(criteriaBuilder.between(rent, it.min, it.max))
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
                        criteriaBuilder.greaterThanOrEqualTo(root.get<LatLng>("location").get("lat"), it.northWest.lat),
                        criteriaBuilder.lessThanOrEqualTo(root.get<LatLng>("location").get("lat"), it.southEast.lat),
                        criteriaBuilder.greaterThanOrEqualTo(root.get<LatLng>("location").get("lng"), it.northWest.lng),
                        criteriaBuilder.lessThanOrEqualTo(root.get<LatLng>("location").get("lng"), it.southEast.lng)
                    )
                )
            }

            criteria.parkingCount?.let {
                predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("parkingCount"), it))
            }

            criteriaBuilder.and(*predicates.toTypedArray())
        }
    }
}
