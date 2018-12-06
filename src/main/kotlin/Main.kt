import com.codav.model.person.BusinessEntities
import com.codav.model.person.Countries
import com.codav.model.person.Departments
import com.codav.model.person.Persons
import org.jetbrains.exposed.dao.IntIdTable
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction
import javax.xml.validation.Schema

fun main(args: Array<String>) {

    Database.connect(
        "jdbc:mysql://localhost:3306/asinc_person?serverTimezone=UTC&useSSL=false&allowPublicKeyRetrieval=true",
        driver = "com.mysql.cj.jdbc.Driver",
        user = "root",
        password = "1234"
    )



    transaction {
        addLogger(StdOutSqlLogger)

        SchemaUtils.create(BusinessEntities)
        SchemaUtils.create(Persons)
        SchemaUtils.create(Countries)
        SchemaUtils.create(Departments)
        //insert new city. SQL: INSERT INTO Cities (name) VALUES ('St. Petersburg')
//        val stPeteId = Cities.insert {
//            it[name] = "Cali"
//        } get Cities.id



        // 'select *' SQL: SELECT Cities.id, Cities.name FROM Cities
//        println("Inserted city: $stPeteId")
        Cities.selectAll().forEachIndexed { index, resultRow -> println("$index - ${resultRow[Cities.name]}")}
    }
}

object Cities: IntIdTable("ciudad") {
    val name = varchar("name", 50)
}
