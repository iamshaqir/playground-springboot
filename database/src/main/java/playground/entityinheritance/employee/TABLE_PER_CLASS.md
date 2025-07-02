This package uses **TABLE_PER_CLASS** Inheritance strategy

#### Uses 
- @Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
- It will make use of base class and sub class properties in every single table and performs a UNION on all the tables to get the result
- Usage of UNION will lead to performance bottlenecks
- We cannot use Identity key generation, should use other strategies

#### Example Query
```text
select
        e1_0.id,
        e1_0.clazz_,
        e1_0.email,
        e1_0.first_name,
        e1_0.hire_date,
        e1_0.last_name,
        e1_0.salary,
        e1_0.contract_company,
        e1_0.contract_end_date,
        e1_0.project_rate,
        e1_0.annual_bonus,
        e1_0.vacation_days,
        e1_0.hourly_rate,
        e1_0.hours_per_week 
    from
        (select
            contract_end_date,
            hire_date,
            project_rate,
            salary,
            id,
            contract_company,
            email,
            first_name,
            last_name,
            cast(null as numeric) as annual_bonus,
            cast(null as integer) as vacation_days,
            cast(null as numeric) as hourly_rate,
            cast(null as integer) as hours_per_week,
            1 as clazz_ 
        from
            contractor_employee 
        union
        all select
            cast(null as date) as contract_end_date,
            hire_date,
            cast(null as numeric) as project_rate,
            salary,
            id,
            cast(null as text) as contract_company,
            email,
            first_name,
            last_name,
            annual_bonus,
            vacation_days,
            cast(null as numeric) as hourly_rate,
            cast(null as integer) as hours_per_week,
            2 as clazz_ 
        from
            full_time_employee 
        union
        all select
            cast(null as date) as contract_end_date,
            hire_date,
            cast(null as numeric) as project_rate,
            salary,
            id,
            cast(null as text) as contract_company,
            email,
            first_name,
            last_name,
            cast(null as numeric) as annual_bonus,
            cast(null as integer) as vacation_days,
            hourly_rate,
            hours_per_week,
            3 as clazz_ 
        from
            part_time_employee
    ) e1_0
```