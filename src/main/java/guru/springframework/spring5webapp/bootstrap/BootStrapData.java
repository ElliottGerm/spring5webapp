package guru.springframework.spring5webapp.bootstrap;

import guru.springframework.spring5webapp.domain.Author;
import guru.springframework.spring5webapp.domain.Book;
import guru.springframework.spring5webapp.domain.Publisher;
import guru.springframework.spring5webapp.repositories.AuthorRepository;
import guru.springframework.spring5webapp.repositories.BookRepository;
import guru.springframework.spring5webapp.repositories.PublisherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootStrapData implements CommandLineRunner {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final PublisherRepository publisherRepository;

    public BootStrapData(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        System.out.println("Starting in Bootstrap");

        //Create publisher
        Publisher superBooksPublishing = new Publisher(
                "Super Books Publishing",
                "101 Fire St.",
                "Virginia Beach",
                "VA",
                23451
        );

        //save newly created publisher to DB
        publisherRepository.save(superBooksPublishing);

        System.out.println("Publisher Count: " + publisherRepository.count());

        //create new author and book, add them to each other
        Author eric = new Author("Eric", "Evans");
        Book ddd = new Book("Domain Driven Design", "123123");
        eric.getBooks().add(ddd);
        ddd.getAuthors().add(eric);

        //add publisher to book ddd and vice versa
        ddd.setPublisher(superBooksPublishing);
        superBooksPublishing.getBooks().add(ddd);

        //save new author, book, and publisher to DB
        authorRepository.save(eric);
        bookRepository.save(ddd);
        publisherRepository.save(superBooksPublishing);

        Author rod = new Author("Rod", "Johnson");
        Book noEJB = new Book("J2EE Development without EJB", "3939459459");
        rod.getBooks().add(noEJB);
        noEJB.getAuthors().add(rod);

        noEJB.setPublisher(superBooksPublishing);
        superBooksPublishing.getBooks().add(noEJB);

        authorRepository.save(rod);
        bookRepository.save(noEJB);
        publisherRepository.save(superBooksPublishing);



        System.out.println("Number of Books: " + bookRepository.count());
        System.out.println("Publisher Number of Books: " + superBooksPublishing.getBooks().size());
    }
}
