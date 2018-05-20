/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package concesionario.vehiculos.umg.concesionario.api.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author DELL
 */
@Entity
@Table(name = "CV_TIPO_VEHICULO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CvTipoVehiculo.findAll", query = "SELECT c FROM CvTipoVehiculo c")
    , @NamedQuery(name = "CvTipoVehiculo.findByIdTipoVehiculo", query = "SELECT c FROM CvTipoVehiculo c WHERE c.idTipoVehiculo = :idTipoVehiculo")
    , @NamedQuery(name = "CvTipoVehiculo.findByExtras", query = "SELECT c FROM CvTipoVehiculo c WHERE c.extras = :extras")
    , @NamedQuery(name = "CvTipoVehiculo.findByDescripcionTipo", query = "SELECT c FROM CvTipoVehiculo c WHERE c.descripcionTipo = :descripcionTipo")
    , @NamedQuery(name = "CvTipoVehiculo.findByFechaCreacion", query = "SELECT c FROM CvTipoVehiculo c WHERE c.fechaCreacion = :fechaCreacion")
    , @NamedQuery(name = "CvTipoVehiculo.findByUsuarioCreacion", query = "SELECT c FROM CvTipoVehiculo c WHERE c.usuarioCreacion = :usuarioCreacion")
    , @NamedQuery(name = "CvTipoVehiculo.findByFechaEliminacion", query = "SELECT c FROM CvTipoVehiculo c WHERE c.fechaEliminacion = :fechaEliminacion")
    , @NamedQuery(name = "CvTipoVehiculo.findByUsuarioEliminacion", query = "SELECT c FROM CvTipoVehiculo c WHERE c.usuarioEliminacion = :usuarioEliminacion")
    , @NamedQuery(name = "CvTipoVehiculo.findByActivo", query = "SELECT c FROM CvTipoVehiculo c WHERE c.activo = :activo")})
public class CvTipoVehiculo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID_TIPO_VEHICULO")
    private Long idTipoVehiculo;
    @Column(name = "EXTRAS")
    private Short extras;
    @Column(name = "DESCRIPCION_TIPO")
    private String descripcionTipo;
    @Column(name = "FECHA_CREACION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCreacion;
    @Column(name = "USUARIO_CREACION")
    private String usuarioCreacion;
    @Column(name = "FECHA_ELIMINACION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaEliminacion;
    @Column(name = "USUARIO_ELIMINACION")
    private String usuarioEliminacion;
    @Column(name = "ACTIVO")
    private Short activo;
    @OneToMany(mappedBy = "idTipoVehiculo", fetch = FetchType.EAGER)
    private List<CvVehiculo> cvVehiculoList;

    public CvTipoVehiculo() {
    }

    public CvTipoVehiculo(Long idTipoVehiculo) {
        this.idTipoVehiculo = idTipoVehiculo;
    }

    public Long getIdTipoVehiculo() {
        return idTipoVehiculo;
    }

    public void setIdTipoVehiculo(Long idTipoVehiculo) {
        this.idTipoVehiculo = idTipoVehiculo;
    }

    public Short getExtras() {
        return extras;
    }

    public void setExtras(Short extras) {
        this.extras = extras;
    }

    public String getDescripcionTipo() {
        return descripcionTipo;
    }

    public void setDescripcionTipo(String descripcionTipo) {
        this.descripcionTipo = descripcionTipo;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public String getUsuarioCreacion() {
        return usuarioCreacion;
    }

    public void setUsuarioCreacion(String usuarioCreacion) {
        this.usuarioCreacion = usuarioCreacion;
    }

    public Date getFechaEliminacion() {
        return fechaEliminacion;
    }

    public void setFechaEliminacion(Date fechaEliminacion) {
        this.fechaEliminacion = fechaEliminacion;
    }

    public String getUsuarioEliminacion() {
        return usuarioEliminacion;
    }

    public void setUsuarioEliminacion(String usuarioEliminacion) {
        this.usuarioEliminacion = usuarioEliminacion;
    }

    public Short getActivo() {
        return activo;
    }

    public void setActivo(Short activo) {
        this.activo = activo;
    }

    @XmlTransient
    public List<CvVehiculo> getCvVehiculoList() {
        return cvVehiculoList;
    }

    public void setCvVehiculoList(List<CvVehiculo> cvVehiculoList) {
        this.cvVehiculoList = cvVehiculoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTipoVehiculo != null ? idTipoVehiculo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CvTipoVehiculo)) {
            return false;
        }
        CvTipoVehiculo other = (CvTipoVehiculo) object;
        if ((this.idTipoVehiculo == null && other.idTipoVehiculo != null) || (this.idTipoVehiculo != null && !this.idTipoVehiculo.equals(other.idTipoVehiculo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "concesionario.vehiculos.umg.concesionario.api.entity.CvTipoVehiculo[ idTipoVehiculo=" + idTipoVehiculo + " ]";
    }
    
}
