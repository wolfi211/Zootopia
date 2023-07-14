package eu.udemx.zootopia.models.entities

import jakarta.persistence.*

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
abstract class IExhibit {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    open var id: Long = 0
    @ManyToOne
    @JoinColumn(name = "exhibit_id")
    open var container: IExhibit? = null
}