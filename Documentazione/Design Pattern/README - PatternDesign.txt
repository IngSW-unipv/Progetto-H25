README PATTERN

FACTORY: gestione della creazione di diversi tipi di party
Reminder: permette l'invio di reminder personalizzati
BaseTicketPrice: permette variazioni di prezzo
PoolParty: reminder per il costume e potrebbe avere un prezzo maggiorato per l’accesso alla piscina.
BBQParty: costo ridotto.
VillaParty: dress code e un prezzo base più alto.
(PS: a rivederlo ora forse era meglio uno strategy)

STRATEGYBANCO: semplicemente modificato i nomi

STRATEGYPAGAMENTO (DA RIVEDERE): 
ContextPagamento: classe principale che utilizza una strategia di pagamento per eseguire un'operazione.
Può cambiare la strategia utilizza il metodo setStrategy.
StrategyPagamento: interfaccia che definisce un metodo comune pagaBiglietto per tutti i tipi di pagamento.
Strategie: Implementano la logica specifica per pagare 
PS: NON SAPREI DOVE METTERLO

FACADE: Un'interfaccia semplificata per gestire i Biglietti, riducendo l'accoppiamento tra l'Organizzatore e lo Staff.
BigliettoManager: Si occupa della creazione, eliminazione e gestione dei biglietti.
DisponibilitàBiglietto: Verifica la disponibilità dei biglietti per un evento.
EmailManager: Gestisce invio email
Organizzatore e Staff interagiscono con il FacadeBiglietto, delegando le operazioni senza conoscere i dettagli dell'implementazione.

PROXY: per aggiungere I biglietti 
Livello intermedio tra i client (Organizzatore o Staff) e il sistema principale di gestione dei biglietti.
Aggiungitore: L'interfaccia o classe astratta che definisce il contratto comune per il Proxy e l'oggetto reale (il metodo aggiungiBiglietto dovrebbe restituire una classe biglietto o IBiglietto?)
OggettoReale: Le classi che si occupano dell'aggiunta dei biglietti.
Client: Classe Biglietto

DECORATOR: Utilizzato per modificare i Biglietti con opzioni aggiuntive.
IBiglietto:Definisce l'interfaccia base per tutti i tipi di biglietti
Biglietto: Oggetto concreto che implementa IBiglietto
AbstractDecorator: Classe astratta che implementa IBiglietto e contiene un riferimento a component (IBiglietto)
Decoratori Concreti: VipDecarator e StaffDecorator aggiunge informazioni in base al ruolo.