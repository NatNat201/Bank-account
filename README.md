# Bank-account

This application allows its user to consult online their bank accounts and see them offline after a first launch.

We use a secured login method by checking the user with their digits. The application require the use of the phone biometrics systems and validate the login if the fingerprints match the ones already saved on the phone. The user can then see their bank accounts.
The application checks the fingerprint a few times and in case the tester's ones don't match, the application will forbid the user from trying too many times and will ask them to try again later.

The first connection needs to be online so the application can load the data and save them from the internet source.
Afterward, the connection can be both on- and offline.
In case the application runs online, the refresh button refreshes the page and allows to consult the present data from the internet.
In case the application runs offline, it load the last data, saved during the last online connection and it had kept on the phone to have the latest data from the last connection.
The file containing the saved data is protected by the phone's system and can't be simply accessed through the files while roaming in the storage.
The internal storage keeps the file accessible to the application without letting the user see the direct content.

The API key is not defined directly in the code, it is declared in another file to prevent getting it from the code.

