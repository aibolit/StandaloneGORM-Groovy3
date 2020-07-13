package app

import org.grails.orm.hibernate.HibernateDatastore
import org.springframework.transaction.annotation.Isolation

import domain.*
import grails.gorm.transactions.Transactional


def configuration = [
        'hibernate.hbm2ddl.auto'    : 'create-drop',
        'dataSource.url'            : 'jdbc:h2:mem:test',
        'dataSource.driverClassName': 'org.h2.Driver',
        'dataSource.logSql'         : 'true'
]

def entities = [Person]
HibernateDatastore datastore = new HibernateDatastore(configuration, *entities)


@Transactional(isolation = Isolation.DEFAULT)
def doSomething() {
    def a = new Person(firstName: "abc", lastName: "def")
    println a.save(flush: true)
}

println "running"
datastore.withNewSession {
    doSomething()
}



