#include <stdio.h>
#include <stdlib.h>



int main(){

    FILE *fp;
    char buffer[1024];
    char command[256];

    // Esempio: Interroga i log da com.apple.xpc.launchd degli ultimi 5 minuti
    snprintf(command, sizeof(command), "log show --last 5m --predicate 'subsystem == \"com.apple.xpc.launchd\"' --info --debug");

    fp = popen(command, "r");
    if (fp == NULL) {
        perror("Impossibile eseguire il comando");
        return 1;
    }

    printf("--- Output da 'log show' ---\n");
    while (fgets(buffer, sizeof(buffer), fp) != NULL) {
        printf("%s", buffer);
    }
    printf("----------------------------\n");

    pclose(fp); // Chiudi la pipe

    return 0;

}