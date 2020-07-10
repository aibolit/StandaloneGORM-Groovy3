/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package app

import org.grails.orm.hibernate.HibernateDatastore
import org.springframework.transaction.annotation.Isolation

import domain.*
import grails.gorm.transactions.Transactional


def configuration = [
    'hibernate.hbm2ddl.auto':'create-drop',
    'dataSource.url': 'jdbc:h2:mem:devDb;MVCC=TRUE;LOCK_TIMEOUT=10000;DB_CLOSE_ON_EXIT=FALSE',
    'dataSource.driverClassName': 'org.h2.Driver',
    'dataSource.logSql': 'true',
    //'dataSource.formatSql': 'true'
]


def entities = [User]
HibernateDatastore datastore = new HibernateDatastore(configuration, *entities)


@Transactional(isolation=Isolation.READ_COMMITTED)
def doSomething() {
    def a = new User(name: "abc", title:"abct", widgets:[w])
    println a.save(flush:true)

}

datastore.withNewSession {doSomething()}



