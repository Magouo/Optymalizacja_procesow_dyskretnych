# Implementacja algorytmu NEH dla problemu przepływowego

Projekt realizuje zadanie implementacji i analizy heurystycznego algorytmu NEH do rozwiązywania problemu szeregowania zadań w systemie przepływowym (flow shop scheduling problem).

## 1. Opis problemu przepływowego

Problem przepływowy (ang. *Flow Shop Scheduling Problem*, FSP) jest jednym z klasycznych problemów optymalizacyjnych w dziedzinie szeregowania zadań. Celem jest znalezienie takiej kolejności (permutacji) \(n\) zadań na \(m\) maszynach, która minimalizuje całkowity czas zakończenia wszystkich zadań, tzw. **makespan** (\(C_{max}\)). Jest to problem NP-trudny.

## 2. Implementacja algorytmów

W ramach projektu zaimplementowano i porównano trzy metody rozwiązywania problemu FSP:
1.  **Algorytm NEH (Nawaz, Enscore, Ham)**: Zaawansowana heurystyka konstrukcyjna.
2.  **Heurystyka Palmera**: Prosta i szybka heurystyka oparta na "indeksie nachylenia".
3.  **Przegląd zupełny (Brute-Force)**: Metoda dokładna, testująca wszystkie \(n!\) permutacji. Służy jako punkt odniesienia do oceny jakości heurystyk.

## 3. Testowanie

### Metodologia

W celu kompleksowego porównania algorytmów, przeprowadzono serię testów na różnych zestawach danych. Zgodnie z zakresem zadania, testy polegały na **zmianie liczby maszyn przy stałej liczbie zadań**.

-   **Parametry stałe**:
    -   Liczba zadań (\(n\)): 9 (wartość pozwalająca na wykonanie metody dokładnej w rozsądnym czasie).
-   **Parametr zmienny**:
    -   Liczba maszyn (\(m\)): testowano dla wartości {3, 5, 8, 10}.
-   **Metryki:**
    -   **Makespan (\(C_{max}\))**: Wartość funkcji celu.
    -   **Czas wykonania (T)**: Mierzony w milisekundach.
    -   **Błąd względny**: Obliczany dla heurystyk względem rozwiązania optymalnego z Brute-Force.

### Wyniki testów

Poniżej przedstawiono zbiorczą tabelę wyników uzyskaną podczas testów.

| # Maszyn | Cmax(P)    | Cmax(N)    | Cmax(Opt)  | T(P)[ms]   | T(N)[ms]   | T(BF)[ms]    | Błąd(P) [%]  | Błąd(N) [%]  |
| :------- | :--------- | :--------- | :--------- | :--------- | :--------- | :----------- | :----------- | :----------- |
| 3        | 412        | 338        | 338        | 7          | 1          | 402          | 21.89        | 0.00         |
| 5        | 496        | 398        | 398        | 0          | 0          | 387          | 24.62        | 0.00         |
| 8        | 570        | 485        | 483        | 0          | 0          | 452          | 18.01        | 0.41         |
| 10       | 674        | 576        | 555        | 0          | 0          | 508          | 21.44        | 3.78         |

*(Legenda: (P)-Palmer, (N)-NEH, (Opt/BF)-Optymalny/Brute-Force, T-Czas)*

## 4. Analiza wyników

### Jakość rozwiązania

Wyniki jednoznacznie potwierdzają **bardzo wysoką skuteczność algorytmu NEH**.
-   Dla 3 i 5 maszyn algorytm NEH **znalazł rozwiązanie optymalne** (błąd 0.00%).
-   Wraz ze wzrostem liczby maszyn błąd pozostał na bardzo niskim poziomie – zaledwie **0.41%** dla 8 maszyn i **3.78%** dla 10 maszyn.
-   W przeciwieństwie do niego, **heurystyka Palmera okazała się znacznie mniej precyzyjna**. Jej błąd względny utrzymywał się na stałym, wysokim poziomie w przedziale **18-25%**, co pokazuje, że jest to metoda dająca jedynie zgrubne oszacowanie.

### Czas wykonania

Analiza czasów wykonania doskonale ilustruje różnice w złożoności obliczeniowej:
-   **Heurystyki (Palmer, NEH)**: Obie metody działają błyskawicznie. Ich czas wykonania jest rzędu pojedynczych milisekund, co czyni je niezwykle praktycznymi.
-   **Metoda dokładna (Brute-Force)**: Czas wykonania jest o **dwa do trzech rzędów wielkości dłuższy** (ok. 400-500 ms). Należy podkreślić, że dla 9 zadań jest to jeszcze akceptowalne, ale już dla 11-12 zadań czas ten wzrósłby do wielu godzin, co pokazuje niepraktyczność metod dokładnych.

### Wnioski

Przeprowadzone testy i analiza w pełni realizują założenia projektu. Wykazano, że zaimplementowany **algorytm NEH stanowi doskonały kompromis między jakością uzyskiwanego rozwiązania a czasem obliczeń**. Generuje on wyniki bardzo bliskie optymalnym (lub optymalne) w czasie pomijalnie krótkim w porównaniu do metod dokładnych. Jednocześnie deklasuje pod względem jakości prostsze heurystyki, takie jak algorytm Palmera, co potwierdza jego status jako jednej z najskuteczniejszych metod heurystycznych dla problemu przepływowego.
