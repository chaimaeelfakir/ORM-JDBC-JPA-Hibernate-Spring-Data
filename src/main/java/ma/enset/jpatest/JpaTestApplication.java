package ma.enset.jpatest;

import ma.enset.jpatest.entities.Patient;
import ma.enset.jpatest.repositories.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import javax.swing.text.AbstractDocument;
import java.util.Date;
import java.util.List;

@SpringBootApplication
public class JpaTestApplication implements CommandLineRunner {

    @Autowired //pour faire l'injection des dépendances (Spring va faire l'implémentation il a déja une implémentation générique)
    private PatientRepository patientRepository;

    public static void main(String[] args) {

        SpringApplication.run(JpaTestApplication.class, args);

    }
    //aprés démarrage de l'application Spring il va faire appel au méthode run pour (faire des tests) exécuter ses commandes

    @Override
    public void run(String... args) throws Exception {

        for(int i=0 ; i<100; i++) {
            patientRepository.save(new Patient(null,"hassan",new Date(),Math.random()>0.5?true:false,(int) (Math.random()*100)) );
        }

        //findAll mauvaise idée lorsqu'on a des centaines d'enregistrements (problème de performance) => La pagination (par spring data)

        //=> Retourner juste la première page
       Page<Patient> patients = patientRepository.findAll(PageRequest.of(0,5));
        System.out.println("Total pages = "+patients.getTotalPages());
        System.out.println("Total elements = "+patients.getTotalElements());
        System.out.println("Num pages = "+patients.getNumber());

        List<Patient> content =  patients.getContent(); //La liste des patients de cette page
        Page<Patient> byMalade = patientRepository.findByMalade(true, PageRequest.of(0,4));

        byMalade.forEach(p->{
            System.out.println("=========================");
            System.out.println(p.getId());
            System.out.println(p.getNom());
            System.out.println(p.getScore());
            System.out.println(p.getDateNaissance());
            System.out.println(p.isMalade());

        });

        System.out.println("***********************");
        //Chercher un patient par ID
        Patient patient = patientRepository.findById(1L).orElseThrow(()->new RuntimeException("Patient not found"));
        //Patient patient = patientRepository.findById(1L).orElse(null);

        if (patient != null) {
            System.out.println(patient.getId());
            System.out.println(patient.getNom());
            System.out.println(patient.getScore());
            System.out.println(patient.getDateNaissance());
            System.out.println(patient.isMalade());
        }

        //Modifier un patient
        patient.setScore(704);
        patientRepository.save(patient);

        System.out.println("********************");

        /** Supprimer un patient
         * patient.deleteById(1L);
         */
        System.out.println("********************");


    }
}
