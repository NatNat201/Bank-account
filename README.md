# Bank-account

This application allows its user to consult online their bank accounts and see them offline after a first launch.

We use a secured login method by checking the user with their digits. The application require the use of the phone biometrics systems and validate the login if the fingerprints match the ones already saved on the phone. The user can then see their bank accounts.
![Login page](https://user-images.githubusercontent.com/62999186/110248802-2bef4600-7f73-11eb-801f-36c0a9d185cc.jpg | width = 100)


The application checks the fingerprint a few times and in case the tester's ones don't match, the application will forbid the user from trying too many times and will ask them to try again later. One can't use the button "see accounts" if the validation didn't work first.
![A problem in login](https://user-images.githubusercontent.com/62999186/110248840-4b866e80-7f73-11eb-975b-166598f89917.jpg)![Login failed](https://user-images.githubusercontent.com/62999186/110248922-b8016d80-7f73-11eb-9bb3-04e599bf24bc.jpg)



If identification is validated, we can access the rest and use the button "see accounts" to change pages and get the data.
![Login successful](https://user-images.githubusercontent.com/62999186/110248937-d36c7880-7f73-11eb-903c-3d975258613a.jpg)



The first connection needs to be online so the application can load the data and save them from the internet source.
Afterward, the connection can be both on- and offline.
![Accounts](https://user-images.githubusercontent.com/62999186/110248991-12023300-7f74-11eb-8435-9eaba232446d.jpg)


In case the application runs online, the refresh button refreshes the page and allows to consult the present data from the internet.
![Refresh](https://user-images.githubusercontent.com/62999186/110248979-06167100-7f74-11eb-9ede-2e14b8ea8bf8.jpg)

In case the application runs offline, it load the last data, saved during the last online connection and it had kept on the phone to have the latest data from the last connection.
![Charging data](https://user-images.githubusercontent.com/62999186/110248959-e717df00-7f73-11eb-9f23-c81cbf521e39.jpg)

The file containing the saved data is protected by the phone's system and can't be simply accessed through the files while roaming in the storage.
The internal storage keeps the file accessible to the application without letting the user see the direct content.

The API key is not defined directly in the code, it is declared in another file to prevent getting it from the code.

