package no.oslomet.cs.algdat.Oblig3;


import java.util.ArrayList;
import java.util.Comparator;
import java.util.Objects;
import java.util.StringJoiner;

public class SBinTre<T> {
    private static final class Node<T>   // en indre nodeklasse
    {
        private T verdi;                   // nodens verdi
        private Node<T> venstre, høyre;    // venstre og høyre barn
        private Node<T> forelder;          // forelder

        // konstruktør
        private Node(T verdi, Node<T> v, Node<T> h, Node<T> forelder) {
            this.verdi = verdi;
            venstre = v;
            høyre = h;
            this.forelder = forelder;
        }

        private Node(T verdi, Node<T> forelder)  // konstruktør
        {
            this(verdi, null, null, forelder);
        }

        @Override
        public String toString() {
            return "" + verdi;
        }

    } // class Node

    private Node<T> rot;                            // peker til rotnoden
    private int antall;                             // antall noder
    private int endringer;                          // antall endringer

    private final Comparator<? super T> comp;       // komparator

    public SBinTre(Comparator<? super T> c)    // konstruktør
    {
        rot = null;
        antall = 0;
        comp = c;
    }

    public boolean inneholder(T verdi) {
        if (verdi == null) return false;

        Node<T> p = rot;

        while (p != null) {
            int cmp = comp.compare(verdi, p.verdi);
            if (cmp < 0) p = p.venstre;
            else if (cmp > 0) p = p.høyre;
            else return true;
        }

        return false;
    }

    public int antall() {
        return antall;
    }

    public String toStringPostOrder() {
        if (tom()) return "[]";

        StringJoiner s = new StringJoiner(", ", "[", "]");

        Node<T> p = førstePostorden(rot); // går til den første i postorden
        while (p != null) {
            s.add(p.verdi.toString());
            p = nestePostorden(p);
        }

        return s.toString();
    }

    public boolean tom() {
        return antall == 0;
    }

    public boolean leggInn(T verdi) {

        if (verdi == null) //nullverdier ikke tillatt
            return false;

        Node<T> p = rot;
        Node<T> q = null;
        int cmp = 0;

        while (p != null) {
            q = p;
            cmp = comp.compare(verdi, p.verdi);
            p = cmp < 0 ? p.venstre : p.høyre;// hvis cmp er mindre enn 0 så setter man venstre barn, ellers høyre
        }

        // p er nå null, dvs. ute av treet, q er den siste vi passerte

        p = new Node<>(verdi, q); //Satt inn q som forelder

        if (q == null) {
            rot = p;
        } else if (cmp < 0) {
            q.venstre = p;
        } else {
            q.høyre = p;
        }
        antall++;
        endringer++;
        return true;
    }

    public boolean fjern(T verdi) {
        throw new UnsupportedOperationException("Ikke kodet ennå!");
    }

    public int fjernAlle(T verdi) {
        throw new UnsupportedOperationException("Ikke kodet ennå!");
    }

    public int antall(T verdi) {
        Node<T> p = rot;
        int antallLike = 0;

        while (p != null) { //Sjekker gjennom nodene, og teller antall ganger verdien dukker opp
            int cmp = comp.compare(verdi, p.verdi);
            if (cmp == 0)
                antallLike++; //Øker antallLike hvis cmp == 0
            p = cmp < 0 ? p.venstre : p.høyre; //Ternary operator, går til venstre hvis sann ellers høyre
        }
        return antallLike;
    }


    public void nullstill() {
        throw new UnsupportedOperationException("Ikke kodet ennå!");
    }

    private static <T> Node<T> førstePostorden(Node<T> p) {

        //Går til venstre, hvis den ikke finnes prøver vi å gå til høyre, ellers leter vi på bunnen
        if (p.venstre != null) {
            return førstePostorden(p.venstre);
        } else if (p.høyre != null) {
            return førstePostorden(p.høyre);
        } else {
            return p;
        }
    }

    private static <T> Node<T> nestePostorden(Node<T> p) {
        if (p.forelder == null) {
            return null;
        }
        if (p.forelder.høyre == p) { //Sjekker om p er høyrebarn, neste postorden forelderen til p
            return p.forelder;
        }
        else if (p.forelder.høyre == null) { // Hvis p er venstre barn, sjekkes det om p er har et høyre søsken, neste postorden forelderen til p
            return p.forelder;
        }
        else { //Om p er venstrebarn og har høyre søsken er denne neste postorden
            return førstePostorden(p.forelder.høyre);
        }
    }


    public void postorden(Oppgave<? super T> oppgave) {
        if(rot == null)
            return;

        //Traverserer over alle noder ved å først finne første i postorden,
        //Deretter finne neste helt til noden blir null.
        Node<T> p = førstePostorden(rot);
        while (p != null) {
            oppgave.utførOppgave(p.verdi);
            p = nestePostorden(p);
        }
    }

    public void postordenRecursive(Oppgave<? super T> oppgave) {
        postordenRecursive(rot, oppgave);
    }

    private void postordenRecursive(Node<T> p, Oppgave<? super T> oppgave) {
        if (p.venstre != null) {  // Går igjennom venstre subtre
            postordenRecursive(p.venstre, oppgave); // metoden kalles med p sin venstre som parameter
        }
        if (p.høyre != null) {  // Går igjennom høyre subtre
            postordenRecursive(p.høyre, oppgave); // Metoden kalles med p sin høyre som parameter
        }
        oppgave.utførOppgave(p.verdi);
    }

    public ArrayList<T> serialize() {
        throw new UnsupportedOperationException("Ikke kodet ennå!");
    }

    static <K> SBinTre<K> deserialize(ArrayList<K> data, Comparator<? super K> c) {
        throw new UnsupportedOperationException("Ikke kodet ennå!");
    }

    public static void main(String[] args) {
        Integer[] a = {4, 7, 2, 9, 4, 10, 8, 7, 4, 6};
        SBinTre<Integer> tre = new SBinTre<>(Comparator.naturalOrder());
        for (int verdi : a) {
            tre.leggInn(verdi);
        }

        System.out.println(tre.antall());      // Utskrift: 10
        System.out.println(tre.antall(5));     // Utskrift: 0
        System.out.println(tre.antall(4));     // Utskrift: 3
        System.out.println(tre.antall(7));     // Utskrift: 2
        System.out.println(tre.antall(10));    // Utskrift: 1
    }


} // ObligSBinTre
