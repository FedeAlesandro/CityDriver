package net.avalith.carDriver.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.avalith.carDriver.models.dtos.requests.ProviderDtoRequest;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "providers")
public class Provider {

    @Id
    @Column(name = "id_provider")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "The name is required")
    @Column(unique = true)
    private String name;

    @Column(name = "is_active")
    private Boolean isActive;

    @Column(name = "created_at")
    @CreationTimestamp
    private Timestamp createdAt;

    @Column(name = "updated_at")
    @UpdateTimestamp
    private  Timestamp updatedAt;

    @OneToMany(mappedBy = "provider", cascade = CascadeType.ALL)
    private List<Vehicle> vehicles = new ArrayList<>();

    public Provider(ProviderDtoRequest providerDtoRequest) {
        this.name = providerDtoRequest.getName();
        this.isActive = Boolean.TRUE;
    }
}
