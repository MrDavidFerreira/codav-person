package com.codav.model.person

import org.jetbrains.exposed.sql.Table

object BusinessEntities: Table("business_entity2") {
    val id = integer(name = "id").primaryKey()
}

object Persons: Table("person2") {
    val id = integer(name = "id_business_entity").primaryKey().references(BusinessEntities.id)
    val firstName = varchar(name = "first_name", length = 45)
    val middleName = varchar(name = "middle_name", length = 45).nullable()
    val lastName = varchar(name = "last_name", length = 45)
    val type = customEnumeration("type", "ENUM ('CUSTOMER','VENDOR','EMPLOYEE')", {value -> PersonType.valueOf(value as String)}, {it.name})

    enum class PersonType { CUSTOMER, VENDOR, EMPLOYEE }
}

object Countries: Table("country2") {
    val id = integer("id").primaryKey()
    val isoCode = varchar(name = "iso_code", length = 3).uniqueIndex()
    val name = varchar(name = "name", length = 45).uniqueIndex()
}

object Departments: Table("deparment2") {
    val id = integer("id").autoIncrement().primaryKey()
    val isoCode = varchar(name = "iso_code", length = 3)
    val name = varchar(name = "name", length = 45)
    val countryId = integer(name = "country_id").references(Countries.id)
}

object Cities: Table("city2") {
    val id = integer("id").autoIncrement().primaryKey()
    val isoCode = varchar(name = "iso_code", length = 3)
    val name = varchar(name = "name", length = 45)
    val countryId = integer(name = "department_id").references(Departments.id)
}

object Addresses: Table("address2") {
    val id = integer("id").autoIncrement().primaryKey()
    val addressLine1 = varchar(name = "address_line_1", length = 60)
    val addressLine2 = varchar(name = "address_line_2", length = 60).nullable()
    val postalCode =  varchar(name = "postal_code", length = 15).nullable()
    val cityId = integer(name = "city_id").references(Cities.id)
}