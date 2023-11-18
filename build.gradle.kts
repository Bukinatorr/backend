plugins {
	kotlin("jvm") version "1.8.22"
	kotlin("plugin.spring") version "1.8.22"
	kotlin("plugin.jpa") version "1.8.22"
	id("org.jlleitschuh.gradle.ktlint") version "11.6.1"
}

group = "xyz.bukinator"

repositories {
	mavenCentral()
}


dependencies {
}

subprojects {
	apply(plugin = "org.jlleitschuh.gradle.ktlint") // Version should be inherited from parent

	repositories {
		// Required to download KtLint
		mavenCentral()
	}

	// Optionally configure plugin
	configure<org.jlleitschuh.gradle.ktlint.KtlintExtension> {
		debug.set(true)
	}
}