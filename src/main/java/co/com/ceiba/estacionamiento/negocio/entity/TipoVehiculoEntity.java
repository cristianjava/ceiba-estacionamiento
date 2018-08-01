package co.com.ceiba.estacionamiento.negocio.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "TIPO_VEHICULO")
public class TipoVehiculoEntity implements Serializable {

	private static final long serialVersionUID = -219131200971094185L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@NotNull
	private String descripcion;

	@OneToMany(mappedBy = "tipoVehiculo", cascade = { CascadeType.ALL }, fetch = FetchType.LAZY)
	private static List<VehiculoEntity> vehiculos;

	public Long getId() {
		return id;
	}

	public void setId(Long idTipoVehiculo) {
		this.id = idTipoVehiculo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
}
