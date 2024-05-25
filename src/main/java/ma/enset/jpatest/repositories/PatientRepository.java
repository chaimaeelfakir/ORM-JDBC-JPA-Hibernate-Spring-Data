package ma.enset.jpatest.repositories;

import ma.enset.jpatest.entities.Patient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

//Spring data (hériter d'une classe générique)
public interface PatientRepository extends JpaRepository<Patient,Long> {
    public List<Patient> findByMalade(boolean m );
    Page<Patient> findByMalade(boolean m, Pageable pageable);

    List<Patient> findByMaladeIsTrueAndScoreLessThan(int score);
    //Methode 1 :
    List<Patient> findByDateNaissanceBetweenAndMaladeIsTrueOrNomLike(Date d1, Date d2, String mc);
    //Methode 2 :
    @Query("select p from Patient p where p.dateNaissance between :x and :y or p.nom like :z")
    List<Patient> chercherPatients(@Param("x") Date d1, @Param("y") Date d2, @Param("z") String nom);
}
