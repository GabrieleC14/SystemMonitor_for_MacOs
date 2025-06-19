#include <stdio.h>
#include <stdlib.h>

void getMemoryStats() {
    FILE *fp;
    char buffer[256];

    fp = popen("vm_stat", "r");
    if (fp == NULL) {
        perror("Errore nell'esecuzione di vm_stat");
        return;
    }

    printf("--- Stato della memoria ---\n");
    while (fgets(buffer, sizeof(buffer), fp) != NULL) {
        printf("%s", buffer);
    }
    printf("---------------------------\n");

    pclose(fp);
}

void getSystemStats() {
    FILE *fp;
    char buffer[1024];

    // Esegui 'top' in modalità batch, 1 iterazione
    fp = popen("top -l 1 | head -n 10", "r");
    if (fp == NULL) {
        perror("Errore nell'esecuzione di top");
        return;
    }

    printf("--- Statistiche sistema ---\n");
    while (fgets(buffer, sizeof(buffer), fp) != NULL) {
        printf("%s", buffer);
    }
    printf("---------------------------\n");

    pclose(fp);
}

int getLogStats(){

   FILE *fp;
    char buffer[1024];
    char command[256];
    //  tramite l'api di apple interrogheremo i log di sistema.
    //  Interroga i log da com.apple.xpc.launchd degli ultimi 5 minuti.
    snprintf(command, sizeof(command), "log show --last 5m --predicate 'subsystem == \"com.apple.xpc.launchd\"' --info --debug");

    fp = popen(command, "r");  //apro il file tramite il command api 
   
   if (fp == NULL) {  //se il file è vuoto o non interagibile restituisce errore
        perror("Impossibile eseguire il comando");
        return 1;
    }

    printf("--- Output da 'log show' ---\n"); //
    while (fgets(buffer, sizeof(buffer), fp) != NULL) { // scorre i log 
        printf("%s", buffer); //stampa
    }
    printf("----------------------------\n"); //lstampa una linea di chiusura (quando è EOF)

    pclose(fp); // Chiudi la pipe
    return 0;
}

int main(){

    int logState = getLogStats();
    getMemoryStats();
    getSystemStats();

    return logState;

}