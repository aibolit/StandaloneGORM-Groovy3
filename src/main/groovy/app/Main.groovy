package app

import org.grails.orm.hibernate.HibernateDatastore
import org.hibernate.dialect.H2Dialect
import org.hibernate.internal.SessionImpl
import org.springframework.transaction.annotation.Isolation

import domain.*
import grails.gorm.transactions.Transactional

def configuration = [
        'hibernate.hbm2ddl.auto'    : 'update',
        'dataSource.url'            : 'jdbc:derby:memory:myDB;create=true',
        'dataSource.logSql'         : 'true',
        'dataSource.dialect'        : 'org.hibernate.dialect.DerbyTenSevenDialect',
]

def entities = [Person]
HibernateDatastore datastore = new HibernateDatastore(configuration, *entities)


@Transactional(isolation = Isolation.DEFAULT)
def doSomething() {
    def a = new Person(firstName: "abc", lastName: "def")
    println a.save(flush: true)
}

println "running"

datastore.withNewSession { SessionImpl s ->
    doSomething()
}



