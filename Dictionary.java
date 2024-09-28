package dictionary;

import java.util.*;

public class Dictionary {

    private HashMap<String, ArrayList<String>> dictionary;

    // Constructor to initialize the HashMap with some data
    public Dictionary() {
        dictionary = new HashMap<>();
        
        ArrayList<String> happySynonyms = new ArrayList<>();
        happySynonyms.add("joyful");
        happySynonyms.add("cheerful");
        happySynonyms.add("content");
        happySynonyms.add("delighted");

        ArrayList<String> sadSynonyms = new ArrayList<>();
        sadSynonyms.add("unhappy");
        sadSynonyms.add("sorrowful");
        sadSynonyms.add("melancholy");
        sadSynonyms.add("downcast");

        ArrayList<String> fastSynonyms = new ArrayList<>();
        fastSynonyms.add("quick");
        fastSynonyms.add("speedy");
        fastSynonyms.add("rapid");
        fastSynonyms.add("swift");

        dictionary.put("happy", happySynonyms);
        dictionary.put("sad", sadSynonyms);
        dictionary.put("fast", fastSynonyms);
    }

    // Method to insert a word and its synonyms
    public void insertWord(String word, ArrayList<String> synonyms) {
        word = word.toLowerCase().trim(); 
        if (dictionary.containsKey(word)) {
            for (String synonym : synonyms) {
                if (!dictionary.get(word).contains(synonym.toLowerCase().trim())) {
                    dictionary.get(word).add(synonym.toLowerCase().trim());
                }
            }
            System.out.println("Synonyms updated for word: " + word);
        } else {
            ArrayList<String> trimmedSynonyms = new ArrayList<>();
            for (String synonym : synonyms) {
                trimmedSynonyms.add(synonym.toLowerCase().trim());
            }
            dictionary.put(word.toLowerCase().trim(), trimmedSynonyms);
            System.out.println("Word added with synonyms: " + word);
        }
    }

    // Method to search for a word and display its synonyms
    public void searchWord(String word) {
        word = word.toLowerCase().trim(); 
        if (dictionary.containsKey(word)) {
            System.out.println("Synonyms for '" + word + "': " + dictionary.get(word));
        } else {
            System.out.println("Word '" + word + "' not found in the dictionary.");
        }
    }

    // Method to delete a word from the dictionary
    public void deleteWord(String word) {
        word = word.toLowerCase().trim(); 
        if (dictionary.containsKey(word)) {
            dictionary.remove(word);
            System.out.println("Word '" + word + "' and its synonyms have been removed.");
        } else {
            System.out.println("Word '" + word + "' not found in the dictionary.");
        }
    }

    // Method to update the synonyms of an existing word
    public void updateWord(String word, ArrayList<String> synonyms, boolean add) {
        word = word.toLowerCase().trim();
        if (dictionary.containsKey(word)) {
            ArrayList<String> wordSynonyms = dictionary.get(word);
            for (String synonym : synonyms) {
                synonym = synonym.toLowerCase().trim();
                if (add) {
                    if (!wordSynonyms.contains(synonym)) {
                        wordSynonyms.add(synonym);
                        System.out.println("Synonym '" + synonym + "' added to word: " + word);
                    }
                } else {
                    if (wordSynonyms.contains(synonym)) {
                        wordSynonyms.remove(synonym);
                        System.out.println("Synonym '" + synonym + "' removed from word: " + word);
                    } else {
                        System.out.println("Synonym '" + synonym + "' not found for word: " + word);
                    }
                }
            }
        } else {
            System.out.println("Word '" + word + "' not found in the dictionary.");
        }
    }

   
    public static void main(String[] args) {
        Dictionary synonymDictionary = new Dictionary();
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        while (!exit) {
            System.out.println("\n1. Insert Word"
                    + "\n2. Search Word"
                    + "\n3. Delete Word"
                    + "\n4. Update Word"
                    + "\n5. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); 

            switch (choice) {
                case 1:
                    // Insert a new word
                    System.out.print("Enter the word: ");
                    String word = scanner.nextLine();
                    System.out.print("Enter synonyms (comma separated): ");
                    String[] synArray = scanner.nextLine().split(",");
                    ArrayList<String> synonyms = new ArrayList<>();
                    for (String syn : synArray) {
                        synonyms.add(syn.trim());
                    }
                    synonymDictionary.insertWord(word, synonyms);
                    break;

                case 2:
                    // Search for a word
                    System.out.print("Enter the word to search: ");
                    word = scanner.nextLine();
                    synonymDictionary.searchWord(word);
                    break;

                case 3:
                    // Delete a word
                    System.out.print("Enter the word to delete: ");
                    word = scanner.nextLine();
                    synonymDictionary.deleteWord(word);
                    break;

                case 4:
                    // Update a word's synonyms
                    System.out.print("Enter the word to update: ");
                    word = scanner.nextLine();
                    System.out.print("Enter synonyms (comma separated): ");
                    synArray = scanner.nextLine().split(",");
                    synonyms = new ArrayList<>();
                    for (String syn : synArray) {
                        synonyms.add(syn.trim());
                    }
                    System.out.print("Add or Remove synonyms? (Enter 'add' or 'remove'): ");
                    String action = scanner.nextLine();
                    synonymDictionary.updateWord(word, synonyms, action.equalsIgnoreCase("add"));
                    break;

                case 5:
                    // Exit the application
                    exit = true;
                    System.out.println("Exiting the application...");
                    break;

                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
        scanner.close();
    }
}
