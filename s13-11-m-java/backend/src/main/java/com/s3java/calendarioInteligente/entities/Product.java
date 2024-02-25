package com.s3java.calendarioInteligente.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "PRODUCT")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    @Column(unique = true)
    private String idUnico;

    @NotBlank
    private String name;

    @NotBlank
    private String instruction;

    @Column(name = "CREATE_DATE")
    private String createDate;

    @NotBlank
    private String description;


    //  TODO: Ver si cambiar a ENUM (activo, en pausa, finalizado, cancelado)


    @Column(name = "state")
    private Boolean state;


    @Column(name = "is_active")
    private Boolean isActive;

    @NotBlank
    @Column(name = "time_estimated_completion")
    private String timeEstimatedCompletion;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "product")
    @JsonManagedReference
    private List<Process> processes = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id")
    @JsonBackReference
    private Company company;

    public Product() {
    }


    public Product(Long id, String idUnico, String name, String instruction, String description, Boolean state, Boolean isActive, String timeEstimatedCompletion){
        this.id = id;
        this.idUnico = idUnico;
        this.name = name;
        this.instruction = instruction;
        this.description = description;
        this.state = state;
        this.isActive = isActive;
        this.timeEstimatedCompletion = timeEstimatedCompletion;
    }

    public List<Process> getProcesses() {
        return processes;
    }

    public void setProcesses(List<Process> processes) {
        this.processes = processes;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIdUnico() {
        return idUnico;
    }

    public void setIdUnico(String idUnico) {
        this.idUnico = idUnico;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInstruction() {
        return instruction;
    }

    public void setInstruction(String instruction) {
        this.instruction = instruction;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }



    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public Boolean getState() {
        return state;
    }

    public void setState(Boolean state) {
        this.state = state;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    public String getTimeEstimatedCompletion() {
        return timeEstimatedCompletion;
    }

    public void setTimeEstimatedCompletion(String timeEstimatedCompletion) {
        this.timeEstimatedCompletion = timeEstimatedCompletion;
    }

    @PrePersist
    public void onPrePersist() {
        this.setCreateDate(LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE));
    }
}
