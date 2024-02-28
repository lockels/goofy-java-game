## Oblig0

Kompetanse til medlemmer:

* Sara - Jeg går andre året på Datateknologi, har blant annet hatt INF100,101 og 102. Ellers hadde vi Haskell forrige semester. Jeg startet å kode når jeg begynte på studiet, så det er den eneste erfaringen jeg har. Jeg har spilt Binding of Isaac. 

* Idris - God i java

* Fredric - INF100, INF101, INF102. 

* Fredrik - Spilt mye "TBOI" (The binding of Isaac) som spillet tar mye inspirasjon fra, erfaring med roguelites (Bra for gamefeel), erfaring med pixel sprites, erfaring i java, erfaring i game dev (Solo og flere)

* Ella - Jeg går første året på Datascience, har hatt blant annet INF100, INF101, INF170.

* Kenan - går andre året på Datasikkerhet. jeg har emner inf100, inf101, inf102. har litt erfaring java og sluttet å spille en stund siden


## INNLEVERING A1 - Team organisering

- Project manager: Ella
- Lead game designer: Fredrik
- Lead artist: Sara
- Lead UI/UX designer: Kenan
- Lead leveldesigner: Fredric
- Lead programmer: Idris

## INNLEVERING A2 - Beskrivelse konsept

- Spillfigurer som kan styres: Isaac, spillerens karakter, kan bevege seg i alle retninger i en todimensjonal verden. I stedet for å hoppe, fokuserer spillet på å unngå fiender og angrep fra fienden ved å bevege seg rundt i rommet.

- Todimensjonal verden:
    - Rom: Spillet er delt inn i rom istedenfor å ha åpne plattformer. Hvert rom kan inneholde fiender, gjenstander, puslespill, eller skatter.
    - Dører: Istedenfor vegger som blokkerer spillerens vei, har rommene dører som fører til andre rom. Noen dører er låst og krever nøkler eller spesielle gjenstander for å åpne.

- Fiender: Spillet inneholder en rekke fiender som beveger seg rundt i rommene og angriper spilleren. Ved slutten av hvert nivå venter en "boss" som må bekjempes for å gå videre.

- Poengsamling og Gjenstander: Spilleren kan samle gjenstander som forbedrer Isaacs evner, som våpen og hjerter for ekstra liv. Disse gjenstandene er kritiske for spillerens suksess.

- Tårer som dreper fiender: Spilleren bruker tårer for å bekjempe fiender.

- Game over: Døden er permanent, og spilleren må starte på nytt ved hvert forsøk.

- Hva vi kan ha med hvis vi utvider spillet:
    - Power-ups: Det er en omfattende mengde "power-ups" som kan endre Isaacs fysiske utseende og spillestil drastisk.
    - Skjulte gjenstander og rom: Spillet inneholder hemmelige rom og gjenstander som krever utforskning og løsning av gåter for å finne.

## INNLEVERING A3 - Prosjekt organisering

**Organisering**
Gruppen vil følge en smidig tilnærming, med hovedvekt på Agile- og scrum-metoden. Vi planlegger å møtes minst en gang i uken for å opprettholde regelmessig kontakt og oppdatere hverandre om prosjektets fremdrift.

### Møter og Hyppighet:
Ukentlige møter(gruppetime) for planlegging, oppdatering og evaluering av prosjektet. Hvis det er behov vil vi møtes flere ganger. 

### Kommunikasjon Mellom Møter:
Kommunikasjon gjennom Discord og Messenger.

### Arbeidsfordeling:
Gruppen har en løs organisering av arbeidsfordeling for å sikre at alle har muligheten til å bidra på tvers av oppgaver og lære forskjellige ferdigheter. Trello brukes som et verktøy for å opprettholde oversikt over oppgaver og prosjektfremdrift. Målet er å skape en fri og naturlig arbeidsflyt som bidrar til fleksibilitet og kompetansedeling i gruppen.

### Oppfølging av Arbeid:
Regelmessige oppdateringer i Git commits og på Trello-kort, det vil også bli snakket om når vi møtes.

### Deling og Oppbevaring av Dokumenter, Diagram og Kodebase:
Git for versjonskontroll og deling av kode. 

**Forventet produkt**
1. Vise et spillebrett
2. Vise spiller på spillebrett
3. Flytte spiller (med piltaster)
4. Spiller interagerer med terreng
5. Spiller har *liv* og *evner* som kan forbedres med gjenstander i spillet(hjerter og våpen) 
6. Vise fiender/monstre; de skal interagere med terreng og spiller
7. Spiller interagerer med fiender (slåss med fiender, miste liv, tårer for å bekjempe fiender)
8. Mål for spillet er å fullføre alle rom.
9. Game over (når spiller har mistet alle liv)
10. Start-skjerm ved oppstart / game over

**Brukerhistorie**

- Som Isaac, den spillbare karakteren, ønsker jeg å utforske todimensjonale rom fylt med fiender, gjenstander og skjulte skatter for å styrke mine evner og overvinne fiender.

1. Når jeg navigerer gjennom rommene, forventer jeg å kunne bevege meg smidig i alle retninger for å unngå fiender og angrep, og ha mulighet til å finne gjenstader som forbedrer mine evner eller gir meg liv.
2. Oppdager jeg en låst dør i et rom, vil jeg se det som en mulighet for å finne en skatt/nøkkel.
3. Når jeg nærmer meg en låst dør, vil jeg delta i en interaktiv handling, ved å finne nøkler eller løse gåter, for å låse opp det neste rommet.
4. Hvis jeg møter en fiende, vil jeg kunne nedkjempe fienden ved hjelp power-ups jeg har fått i løpet av spillet. 
5. For å øke utfordringen vil jeg at skattene skal variere avhengig av rommets vanskelighetsgrad. Noen ganger kan det være skjulte rom med ekstra utfordringer eller gåter for å finne unike power-ups som endrer Isaacs utseende og spillestil drastisk, og dermed gi en følelse av progresjon og belønning.



## INNLEVERING A4 - Oppsett av kode-skjelett

Det er begynt på rammeverk/kode

## INNLEVERING A5 - Oppsummering

Hva som gikk bra:
Valg av prosjekt: Enigheten rundt valget av "The Binding of Isaac" som vårt prosjekttema gikk svært fint. Så prossen hvor alt fungerte som forventet. 
Bruk av verktøy: Oppsett og bruk av Trello for prosjektstyring og andre applikasjoner for samarbeid gikk veldig bra. Disse verktøyene hjalp oss med å holde styr på oppgaver, frister og ansvarsområder på en effektiv måte.
Utfordringer:
Tidsplanlegging for møter: Å finne felles tid for møter i ukedagene viste seg å være utfordrende. Dette skyldtes hovedsakelig sammensatte timeplaner og andre forpliktelser blant teammedlemmene.
Arbeidsfordeling: Selv om prosjektet i stor grad gikk som planlagt, møtte vi noen hindringer med hensyn til optimal arbeidsfordeling. Til tider var det usikkerhet om hvem som skulle gjøre hva, noe som førte til mindre forsinkelser.
Mislykkede eller problematiske områder:
Heldigvis hadde vi ingen områder som ikke fungerte i det hele tatt. De fleste utfordringene vi møtte, ble adressert og løst i løpet av prosjektperioden.
Nye aktiviteter eller verktøy for neste oppgave:
Mer fleksible møtetider: Vi planlegger å utforske mer fleksible møtetider eller stående møter for å imøtekomme alles timeplaner bedre.
Klarere rollefordeling: 
For å forbedre arbeidsfordelingen i vårt neste prosjekt, har vi besluttet å definere klare roller og ansvarsområder fra starten:
Project Manager: Organisere møter, delegere oppgaver, og håndtere GIT.
Game Producer/Designer: Holder den kreative visjonen og definerer spillkonseptet.
Lead Artist: Ansvarlig for spillgrafikk, inkludert design og animasjoner.
Lead Sound Engineer: Styrer spillets lydeffekter og musikk.
Lead UI/UX Designer: Fokuserer på brukervennlighet og design av brukergrensesnitt.
Lead Level Designer: Utvikler spillets nivåer for optimal spillerengasjement.
Lead Programmer: Håndterer all spillrelatert programmering.
Tidsstyringsverktøy: Vi vurderer å ta i bruk nye verktøy for tidsstyring for bedre å holde oversikt over frister og forpliktelser.
Vurdering og justering for neste iterasjon:
Selv om vi generelt traff godt på våre mål for denne oppgaven, anerkjenner vi behovet for bedre tidsplanlegging og klarere arbeidsfordeling. Disse områdene vil bli fokusert på i planleggingen av vår neste iterasjon. Basert på erfaringene fra denne oppgaven, vil vi justere vår tilnærming til tidsbruk og oppgavevanskelighet for å sikre en mer realistisk og oppnåelig arbeidsmengde i neste iterasjon.