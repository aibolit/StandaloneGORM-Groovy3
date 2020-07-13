package domain

import grails.gorm.annotation.Entity
import groovy.transform.ToString

@Entity
@ToString(includeNames = true)
class Person {
    String firstName
    String lastName
}
