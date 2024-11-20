package champollion;

import lombok.Getter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.ServiceConfigurationError;

/**
 * Un enseignant est caractérisé par les informations suivantes : son nom, son adresse email, et son service prévu,
 * et son emploi du temps.
 */
public class Enseignant extends Personne {

    // TODO : rajouter les autres méthodes présentes dans le diagramme UML

    // Attribut
    private Map<UE, Intervention> interventions;

    private Map<UE, ServicePrevu> EDT;

    public Enseignant(String nom, String email) {
        super(nom, email);
        this.EDT = new HashMap<UE, ServicePrevu>();
        this.interventions = new HashMap<UE, Intervention>();
    }


    public Map<UE, Intervention> getInterventions() {
        return interventions;
    }


    /**
     * Calcule le nombre total d'heures prévues pour cet enseignant en "heures équivalent TD" Pour le calcul : 1 heure
     * de cours magistral vaut 1,5 h "équivalent TD" 1 heure de TD vaut 1h "équivalent TD" 1 heure de TP vaut 0,75h
     * "équivalent TD"
     *
     * @return le nombre total d'heures "équivalent TD" prévues pour cet enseignant, arrondi à l'entier le plus proche
     *
     */
    public int heuresPrevues() {
        // TODO: Implémenter cette méthode
        int total = 0;
        for (ServicePrevu sp : EDT.values()) {
            total += (int) ((sp.volumeCM +1.5) + sp.volumeTD + (sp.volumeTD*0.75));
        }
        return total;
        //throw new UnsupportedOperationException("Pas encore implémenté");
    }

    /**
     * Calcule le nombre total d'heures prévues pour cet enseignant dans l'UE spécifiée en "heures équivalent TD" Pour
     * le calcul : 1 heure de cours magistral vaut 1,5 h "équivalent TD" 1 heure de TD vaut 1h "équivalent TD" 1 heure
     * de TP vaut 0,75h "équivalent TD"
     *
     * @param ue l'UE concernée
     * @return le nombre total d'heures "équivalent TD" prévues pour cet enseignant, arrondi à l'entier le plus proche
     *
     */
    public int heuresPrevuesPourUE(UE ue) {
        // TODO: Implémenter cette méthode
        int total = 0;
        ServicePrevu prev = EDT.get(ue);
        if (prev != null) {
            total += (int) ((prev.volumeCM *1.5 ) + prev.volumeTD + (prev.volumeTP *0.75));
        }
        return total;
        //throw new UnsupportedOperationException("Pas encore implémenté");
    }

    /**
     * Ajoute un enseignement au service prévu pour cet enseignant
     *
     * @param ue l'UE concernée
     * @param volumeCM le volume d'heures de cours magistral
     * @param volumeTD le volume d'heures de TD
     * @param volumeTP le volume d'heures de TP
     */
    public void ajouteEnseignement(UE ue, int volumeCM, int volumeTD, int volumeTP) {
        // TODO: Implémenter cette méthode
        ServicePrevu sp = EDT.get(ue);
        if (sp == null) {
            sp = new ServicePrevu(ue, volumeCM, volumeTD, volumeTP);
            EDT.put(ue, sp);
        }else{
            sp.setVolumeCM(volumeCM + sp.volumeCM);
            sp.setVolumeTD(volumeTD + sp.volumeTD);
            sp.setVolumeTP(volumeTP + sp.volumeTP);
        }

        //throw new UnsupportedOperationException("Pas encore implémenté");
    }

    public void ajouteIntervention(String date, int duree, int h_deb, UE ue) {
        ArrayList<Intervention> tabi = new ArrayList<Intervention>();
        tabi.add(interventions.get(ue));
        if (tabi.isEmpty()) {
            Intervention i = new Intervention(date, duree, h_deb, ue);
            interventions.put(ue, i);
        }else{
            int totalTmpsInterv = 0;
            int totalTmpsEDT = heuresPrevuesPourUE(ue);
            for (Intervention interv : interventions.values()){
                totalTmpsInterv += interv.getDuree();
            }
            if (totalTmpsInterv < totalTmpsEDT && (totalTmpsInterv + duree) < totalTmpsEDT) {
                Intervention i = new Intervention(date, duree, h_deb, ue);
                interventions.put(ue, i);
            }else{
                throw new IllegalArgumentException("Le temps d'intervention dépasse le temps prévu à l'EDT");
            }
        }
    }

    public int resteAPlanifier(UE ue){
        int totalTmpsEDT = heuresPrevuesPourUE(ue);
        int totalTmpsInterv = 0;
        Intervention i = interventions.get(ue);
        for (Intervention interv : interventions.values()){
            totalTmpsInterv += interv.getDuree();
        }
        return  totalTmpsEDT - totalTmpsInterv;
    }

    public boolean enSousService(){
        int totalTmpsPrevu = heuresPrevues();
        if (totalTmpsPrevu < 192){
            return false;
        }else{
            return true;
        }
    }

}
