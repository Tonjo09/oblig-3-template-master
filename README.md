# Obligatorisk oppgave 3 i Algoritmer og Datastrukturer

Denne oppgaven er en innlevering i Algoritmer og Datastrukturer. 
Oppgaven er levert av følgende student:
* Tony Nguyen, s341492, s341492@oslomet.no


# Oppgavebeskrivelse

Oppgave 1: Løse oppgaven ved hjelp av 5.2.3 fra kompendiet

Metoden oppretter en node p som peker på rotnoden, som går igjennom treet så lenge det finnes noder, og setter q som p sin forelder:
Her sammenlignes verdien med p sin verdi og så lenge den er større går man til høyre subtre og så lenge den er mindre går man til venstre subtre.
Når man er kommet til enden og q ikke har noen barn, så opprettes ny node med verdi
Her måtte jeg i tillegg legge inn forelder-peker (q) til den nye noden som opprettes (p = new Node(verdi, q);)
Hvis q finnes skal p legges inn som barn til q: sjekker igjen om den skal gå til høyre eller venstre subtre og legge inn

Oppgave 2: Løst oppgaven ved hjelp av avsnitt 5.2.6 i kompendiet

Hvis det ikke er noen verdier i treet skal den returnere 0
Det er opprettet en while loop som skal sjekke gjennom nodene og teller antall ganger verdien dukker opp.
Til slutt returneres teller som har talt opp antall tilfeller man har funnet verdien

Oppgave 3: Tatt utgangspunkt i avsnitt 5.1.7 og 5.1.15 (og programkode 5.1.7 h) i kompendiet

I metoden førstePostorden starter jeg i rot-noden og går så først igjennom venstre subtre, også høyre subtre.
Så lenge p har et barn så skal metoden kalle på seg selv ved å gå nedover i venstre subtre helt til p ikke har noe venstre barn
Det samme skal så gjøres i høyre subtre, bare at man sjekker at p har et høyrebarn og stopper når den ikke har noe høyre barn.
Når både venste- og høyre subtre har blitt gått igjennom - skal funksjonen returnere noden p
NestePostorden:

Når man har funnet første i postorden må man tenke at man går videre til neste. For å finne neste må man derfor sjekke om p har søsken eller ikke, for å avgjøre om neste blir høyre søsken-node eller forelder-noden.
Må derfor sjekke om p har søsken:
hvis p er høyrebarnet blir det forelder som er neste i postorden
men hvis p er enebarn (kun er venstrebarn) trenger man ikke gå innom noe høyrebarn først og forelder er også da neste
men hvis p har et høyre søsken, så er det denne som er neste i postorden: derfor kaller man på førstePostorden(p.forelder.høyre)

Oppgave 4: Tatt utgangspunkt i avsnitt 5.1.10, 5.1.15 og 5.1.7 

Måtte først finne den første noden i postorden, deretter traverserer postorden-metoden fra rot-noden til man finner p
Så brukes metoden nestePostorden for å finne p sin neste i postorden og deretter traverserer nestePostorden-metoden for å finne p her også
Når det ikke er flere noder har vi funnet p, og postorden-metoden skriver ut p sin verdi
Metoden skal her kalle på seg selv så lenge p har venstre barn og høyre barn, når det er gått igjennom både venstre og høyre subtre og p ikke har flere barn skal den til slutt returnere p sin verdi

Oppgave 5: 
I denne metoden skal man traversere treet i nivåorden 
Hvis binærtreet inneholder noder med verdier skrives verdiene ut med en verdi for hver iterasjon.
Må lage en (ArrayList kalt) kø der instanser av Node legges inn i køen --> denne skrives ut til konsollet.
Derfor må man lage en liste som skal skrives ut, der først rotnoden legges inn, så barna, og slik fortsetter det helt til det ikke er flere noder i køen.
Når det ikke er flere i køen skrives listen skrivUt ut med alle verdiene satt inn.

deserialize-metoden har jeg tatt utgangspunkt i måten jeg har satt inn en arrayliste i et tre når jeg skal sjekke metodene mine i main.
Da lager jeg et tomt tre, hvor jeg går gjennom ArrayList-listen min fra serialize-metoden og setter inn en og en verdi ved bruk av leggInn-metoden. Og til slutt returnerer jeg det utfylte binærtreet.
