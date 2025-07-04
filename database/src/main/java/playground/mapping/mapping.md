# Spring Data JPA Mapping

## OneToOne Mapping Uni directional

- UserDetails -> parent and UserAddress -> child
- UserDetails has a foreign key column, by default jpa will name it as field name in parent table for child table_primary key field name of child table(`user_address_id`)
- If you want a custom name use `@JoinColumn` in parent table, 
    ```java
    @OneToOne
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    private UserAddress userAddress;
    ```

By default, jpa will refer primary key of child table, what if there is composite key in child table?? 
- Create a class with composite key using `@IdClass` or `@Embeddable` and `@EmbeddedId` concepts with `@JoinColumns` as
    ```java
    @OneToOne
    @JoinColumns(value = {
       @JoinColumn(name = "address_street", referencedColumnName = "street"),
       @JoinColumn(name = "address_pincode", referencedColumnName = "pincode")
    })
    private UserAddress userAddress;
    ```

### Cascade type
- PERSIST, MERGE, REMOVE, REFRESH, DETACH
- If parent is removed/updated child should also be removed
- Without cascade type any operation on parent does not affect child entity
- Managing child entity explicitly can be error prone

##### CascadeType.PERSIST
- let's suppose you are adding user details and user address should also need to added, if we don't use `CascadeType.PERSIST`, it'll say `save the transient instance before flushing`, use below
    ```text
    Caused by: org.hibernate.TransientObjectException: persistent instance references an unsaved transient instance of 'playground.mapping.onetoone.uni.UserAddress' (save the transient instance before flushing)
    ```
    ```java
    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    private UserAddress userAddress;
    ```
##### CascadeType.MERGE
- If you are updating a child table, here in our case User Address, if you do not add `CascadeType.MERGE`, jpa will throw an error saying <br/>
  `org.hibernate.PersistentObjectException: detached entity passed to persist: playground.mapping.onetoone.uni.UserAddress`
  ```java
    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    private UserAddress userAddress;
  ```
##### CascadeType.REMOVE
- if you do not provide `CascadeType.REMOVE` it will not remove `child entity`

##### CascadeType.DETACH and CascadeType.REFRESH
- Jpa has persistence context and is used for first level caching, when we call save method to save entity it uses `entityManager.persist()`, similar to that we have few more methods like `entityManager.detach()`, `entityManager.refresh()`
- `entityManager.detach()` will make sure that entity is not `managed by jpa persistence` and when we use `CascadeType.DETACH` it makes sure jpa should not manage that entity, and it's associated child entities
- `entityManager.refresh()` is used to `refresh data directly from database`, `CascadeType.REFRESH` will make sure, current entity, and it's associated child entities are fetched from database.

## OneToOne Mapping Bi directional
- Bi-directional mapping is used to retrieve data from both ends, parent-to-child and child-to-parent
- Need to refer the parent entity in child and map it child's reference in child class
```java
    @OneToOne(mappedBy = "userAddress")
    private UserDetails userDetails;
```
- Here during response serialization, jackson will create child response and refer parent instance and then create parent response, now that parent has child reference, it will cause infinite response creation until exception is thrown
- Make sure to handle this case using jackson annotations `@JsonManagedReference` and `@JsonBackReference`
- @JsonManagedReference is used on owning entity, inform json to serialize child entity
```java
    @JsonManagedReference
    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE})
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    private UserAddress userAddress;
```
- `@JsonBackReference` is used on Inverse side, inform json not to serialize parent entity
```java
    @JsonBackReference
    @OneToOne(mappedBy = "userAddress")
    private UserDetails userDetails;
```
- NOTE: This will not give us parent data rather we will get only child data, if you want to include parent data as well, we need to use `@JsonIdentityInfo` and remove `@JsonManagedReference` and `@JsonBackReference`
- During serialization , Jackson gives the unique ID to the entity (based on property field), through which Jackson can know if that particular id entity is already serialized before, then it skip the serialization.
- Add on the both parent and child class and make sure to provide a unique value in property scope
```java
@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class UserDetails {
}
```





























