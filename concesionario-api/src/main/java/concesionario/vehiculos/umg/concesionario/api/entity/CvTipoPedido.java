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
@Table(name = "CV_TIPO_PEDIDO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CvTipoPedido.findAll", query = "SELECT c FROM CvTipoPedido c")
    , @NamedQuery(name = "CvTipoPedido.findByIdTipoPedido", query = "SELECT c FROM CvTipoPedido c WHERE c.idTipoPedido = :idTipoPedido")
    , @NamedQuery(name = "CvTipoPedido.findByNombre", query = "SELECT c FROM CvTipoPedido c WHERE c.nombre = :nombre")
    , @NamedQuery(name = "CvTipoPedido.findByFechaCreacion", query = "SELECT c FROM CvTipoPedido c WHERE c.fechaCreacion = :fechaCreacion")
    , @NamedQuery(name = "CvTipoPedido.findByUsuarioCreacion", query = "SELECT c FROM CvTipoPedido c WHERE c.usuarioCreacion = :usuarioCreacion")
    , @NamedQuery(name = "CvTipoPedido.findByFechaEliminacion", query = "SELECT c FROM CvTipoPedido c WHERE c.fechaEliminacion = :fechaEliminacion")
    , @NamedQuery(name = "CvTipoPedido.findByUsuarioEliminacion", query = "SELECT c FROM CvTipoPedido c WHERE c.usuarioEliminacion = :usuarioEliminacion")
    , @NamedQuery(name = "CvTipoPedido.findByActivo", query = "SELECT c FROM CvTipoPedido c WHERE c.activo = :activo")})
public class CvTipoPedido implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID_TIPO_PEDIDO")
    private Long idTipoPedido;
    @Basic(optional = false)
    @Column(name = "NOMBRE")
    private String nombre;
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
    @OneToMany(mappedBy = "idTipoPedido", fetch = FetchType.EAGER)
    private List<CvPedido> cvPedidoList;

    public CvTipoPedido() {
    }

    public CvTipoPedido(Long idTipoPedido) {
        this.idTipoPedido = idTipoPedido;
    }

    public CvTipoPedido(Long idTipoPedido, String nombre) {
        this.idTipoPedido = idTipoPedido;
        this.nombre = nombre;
    }

    public Long getIdTipoPedido() {
        return idTipoPedido;
    }

    public void setIdTipoPedido(Long idTipoPedido) {
        this.idTipoPedido = idTipoPedido;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
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
    public List<CvPedido> getCvPedidoList() {
        return cvPedidoList;
    }

    public void setCvPedidoList(List<CvPedido> cvPedidoList) {
        this.cvPedidoList = cvPedidoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTipoPedido != null ? idTipoPedido.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CvTipoPedido)) {
            return false;
        }
        CvTipoPedido other = (CvTipoPedido) object;
        if ((this.idTipoPedido == null && other.idTipoPedido != null) || (this.idTipoPedido != null && !this.idTipoPedido.equals(other.idTipoPedido))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "concesionario.vehiculos.umg.concesionario.api.entity.CvTipoPedido[ idTipoPedido=" + idTipoPedido + " ]";
    }
    
}
