package concesionario.vehiculos.umg.concesionario.bussines.ejb.impl;

import concesionario.vehiculos.umg.concesionario.api.ejb.ConcesionarioBeanLocal;
import concesionario.vehiculos.umg.concesionario.api.entity.CvConcesionario;
import concesionario.vehiculos.umg.concesionario.api.entity.CvProveedor;
import concesionario.vehiculos.umg.concesionario.api.entity.CvServicioOficial;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.Resource;
import javax.ejb.EJBContext;
import javax.ejb.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import org.apache.log4j.Logger;

/**
 *
 * @author DELL
 */
@Singleton
public class ConcesionarioBean implements ConcesionarioBeanLocal {

    private static final Logger log = Logger.getLogger(ConcesionarioBeanLocal.class);

    @PersistenceContext(unitName = "ConceVehiculosPU")
    EntityManager em;

    @Resource
    private EJBContext context;

    private void processException(Exception ex) {
        log.error(ex.getMessage(), ex);
    }

    private String getConstraintViolationExceptionAsString(ConstraintViolationException ex) {
        StringBuilder sb = new StringBuilder();
        sb.append("Error de validación:\n");
        for (ConstraintViolation c : ex.getConstraintViolations()) {
            sb.append(String.format("[bean: %s; field: %s; message: %s; value: %s]",
                    c.getRootBeanClass().getName(),
                    c.getPropertyPath().toString(),
                    c.getMessage(), c.getInvalidValue()));
        }
        return sb.toString();
    }

    @Override
    public List<CvConcesionario> ListaConcesionarios() {
        List<CvConcesionario> lst = em.createQuery("SELECT conce FROM CvConcesionario conce WHERE conce.activo = true", CvConcesionario.class)
                .getResultList();

        if (lst == null || lst.isEmpty()) {
            return null;
        }

        return lst;
    }

    @Override
    public CvConcesionario saveConcesionario(CvConcesionario concesionario) {
        try {
            concesionario.setFechaCreacion(new Date());
            concesionario.setActivo(true);

            em.persist(concesionario);
            em.flush();
            return (concesionario);
        } catch (ConstraintViolationException ex) {
            String validationError = getConstraintViolationExceptionAsString(ex);
            log.error(validationError);
            context.setRollbackOnly();
            return null;
        } catch (Exception ex) {
            processException(ex);
            context.setRollbackOnly();
            return null;
        }
    }

    @Override
    public CvConcesionario findConcesionario(Integer idConcesionario) {
        List<CvConcesionario> lst = em.createQuery("SELECT conce FROM CvConcesionario conce WHERE conce.idConcesionario =:idConcesionario and conce.activo = true", CvConcesionario.class)
                .setParameter("idConcesionario", idConcesionario)
                .getResultList();

        if (lst == null || lst.isEmpty()) {
            return null;
        }

        return lst.get(0);
    }

    @Override
    public CvConcesionario updateConcesionario(CvConcesionario concesionario) {
        if (concesionario == null) {
            context.setRollbackOnly();
            return null;
        }
//        if (sesion == null) {
//            context.setRollbackOnly();
//           
//        }
        try {
            CvConcesionario toUpdate = em.find(CvConcesionario.class, concesionario.getIdConcesionario());

            toUpdate.setNombre(concesionario.getNombre());
            toUpdate.setDireccion(concesionario.getDireccion());

            if (concesionario.getActivo() == Boolean.FALSE) {
                toUpdate.setFechaEliminacion(new Date());
                toUpdate.setActivo(false);
            }

            em.merge(toUpdate);

            return concesionario;
        } catch (ConstraintViolationException ex) {
            String validationError = getConstraintViolationExceptionAsString(ex);
            log.error(validationError);
            context.setRollbackOnly();
            return null;
        } catch (Exception ex) {
            processException(ex);
            return null;
        }
    }

    @Override
    public List<CvServicioOficial> ListaServiciosOficiales() {
        List<CvServicioOficial> lst = em.createQuery("SELECT oficial FROM CvServicioOficial oficial WHERE oficial.activo = true", CvServicioOficial.class)
                .getResultList();

        if (lst == null || lst.isEmpty()) {
            return null;
        }

        return lst;
    }

    @Override
    public CvServicioOficial saveServicioOficial(CvServicioOficial servicioOficial) {
        try {
            servicioOficial.setFechaCreacion(new Date());
            servicioOficial.setActivo(true);

            em.persist(servicioOficial);
            em.flush();
            return (servicioOficial);
        } catch (ConstraintViolationException ex) {
            String validationError = getConstraintViolationExceptionAsString(ex);
            log.error(validationError);
            context.setRollbackOnly();
            return null;
        } catch (Exception ex) {
            processException(ex);
            context.setRollbackOnly();
            return null;
        }
    }

    @Override
    public CvServicioOficial findServicioOficial(Integer idServicioOficial) {
        List<CvServicioOficial> lst = em.createQuery("SELECT oficial FROM CvServicioOficial oficial WHERE oficial.idServicioOficial =:idServicioOficial and oficial.activo = true", CvServicioOficial.class)
                .setParameter("idServicioOficial", idServicioOficial)
                .getResultList();

        if (lst == null || lst.isEmpty()) {
            return null;
        }

        return lst.get(0);
    }

    @Override
    public CvServicioOficial updateServicioOficial(CvServicioOficial servicioOficial) {
        if (servicioOficial == null) {
            context.setRollbackOnly();
            return null;
        }
//        if (sesion == null) {
//            context.setRollbackOnly();
//           
//        }
        try {
            CvServicioOficial toUpdate = em.find(CvServicioOficial.class, servicioOficial.getIdServicioOficial());

            toUpdate.setNombre(servicioOficial.getNombre());
            toUpdate.setDireccion(servicioOficial.getDireccion());
            toUpdate.setTelefono(servicioOficial.getTelefono());
            toUpdate.setCorreoElectronico(servicioOficial.getCorreoElectronico());

            if (servicioOficial.getActivo() == Boolean.FALSE) {
                toUpdate.setFechaEliminacion(new Date());
                toUpdate.setActivo(false);
            }

            em.merge(toUpdate);

            return servicioOficial;
        } catch (ConstraintViolationException ex) {
            String validationError = getConstraintViolationExceptionAsString(ex);
            log.error(validationError);
            context.setRollbackOnly();
            return null;
        } catch (Exception ex) {
            processException(ex);
            return null;
        }
    }

    @Override
    public CvServicioOficial findServicioOficialByIdConcesionario(Integer idConcesionario) {
        List<CvServicioOficial> lst = em.createQuery("SELECT oficial FROM CvServicioOficial oficial WHERE oficial.idConcesionario.idConcesionario =:idConcesionario and oficial.activo = true", CvServicioOficial.class)
                .setParameter("idConcesionario", idConcesionario)
                .getResultList();

        if (lst == null || lst.isEmpty()) {
            return null;
        }

        return lst.get(0);
    }

    @Override
    public CvProveedor saveProveedor(CvProveedor proveedor) {
        try {
            proveedor.setFechaCreacion(new Date());
            proveedor.setActivo(true);

            em.persist(proveedor);
            em.flush();
            return (proveedor);
        } catch (ConstraintViolationException ex) {
            String validationError = getConstraintViolationExceptionAsString(ex);
            log.error(validationError);
            context.setRollbackOnly();
            return null;
        } catch (Exception ex) {
            processException(ex);
            context.setRollbackOnly();
            return null;
        }
    }

    @Override
    public List<CvServicioOficial> ListaServiciosOficialesByIdConcesionario(Integer idConcesionario) {
        List<CvServicioOficial> lst = em.createQuery("SELECT oficial FROM CvServicioOficial oficial WHERE oficial.idConcesionario.idConcesionario =:idConcesionario and oficial.activo = true", CvServicioOficial.class)
                .setParameter("idConcesionario", idConcesionario)
                .getResultList();

        if (lst == null || lst.isEmpty()) {
            return null;
        }

        return lst;
    }

    @Override
    public List<CvProveedor> listaProveedoresByIdConcesionario(Integer idConcesionario) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        ArrayList<Predicate> lstPredicates = new ArrayList<>();

        CriteriaQuery<CvProveedor> query = cb.createQuery(CvProveedor.class);
        Root<CvProveedor> proveedor = query.from(CvProveedor.class);

        Join<CvProveedor, CvConcesionario> conce = proveedor.join("idProveedor");

        Predicate pActivo = cb.equal(proveedor.<Boolean>get("activo"), Boolean.TRUE);
        lstPredicates.add(pActivo);

        Predicate pConce = cb.equal(conce.<Integer>get("idConcesionario"), idConcesionario);
        lstPredicates.add(pConce);

        query.where(cb.and(lstPredicates.toArray(new Predicate[lstPredicates.size()])));

        List<CvProveedor> lstProveedores = em.createQuery(query).getResultList();

        if (lstProveedores == null || lstProveedores.isEmpty()) {
            return null;
        }

        return lstProveedores;
    }

}