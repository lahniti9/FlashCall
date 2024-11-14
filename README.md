# FlashCall

**FlashCall** est une application Android qui utilise le flash de l'appareil pour clignoter lors des appels entrants. Cette application est conçue pour aider les utilisateurs à recevoir des notifications visuelles via le flash de leur téléphone.

## Fonctionnalités

- **Clignotement du flash lors des appels entrants** : Le flash de l'appareil clignote à chaque appel entrant.
- **Contrôle du flash** : Le flash peut être activé ou désactivé selon l'état de l'appel (appels entrants).
- **Service en arrière-plan** : L'application fonctionne en arrière-plan pour garantir que le flash clignote même lorsque l'application n'est pas ouverte.

## Prérequis

- Android 6.0 (API 23) ou version supérieure.
- Permissions nécessaires pour accéder à la caméra et à l'état du téléphone.

## Installation

### Cloner le dépôt

```bash
git clone https://github.com/lahniti9/FlashCall.git
Configuration du projet
Ouvrez le projet dans Android Studio.
Synchronisez le projet avec Gradle.
Configurez les permissions dans le fichier AndroidManifest.xml :

xml
<uses-permission android:name="android.permission.CAMERA"/>
<uses-permission android:name="android.permission.READ_PHONE_STATE"/>
Assurez-vous que votre appareil est configuré pour tester l'application sur un appareil physique ou un émulateur compatible.

Utilisation
L'application démarrera en arrière-plan une fois lancée.
Lorsque vous recevrez un appel entrant, le flash commencera à clignoter.
L'application arrêtera de clignoter lorsque l'appel sera terminé ou rejeté.
