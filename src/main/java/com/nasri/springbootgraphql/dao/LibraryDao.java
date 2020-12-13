package com.nasri.springbootgraphql.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import com.nasri.springbootgraphql.dto.Author;
import com.nasri.springbootgraphql.dto.Book;
import com.nasri.springbootgraphql.dto.Library;

@Repository
public class LibraryDao {

	private final Random random = new Random();

	private final List<Library> libraries;

	private final List<Book> books;

	private final List<Author> authors;

	public LibraryDao() {
		this.libraries = new ArrayList<>();
		initLibraries();

		this.books = getBooksInternal();

		this.authors = getAuthorsInternal();
	}

	public List<Library> getLibraries(int count) {
		return this.libraries.stream().limit(count).collect(Collectors.toList());
	}

	public Library getLibrary(Long id) {
		return this.libraries.stream().collect(Collectors.toMap(Library::getId, Function.identity())).get(id);
	}

	public List<Book> recentBooks(int count) {
		return this.books.stream().limit(count).collect(Collectors.toList());
	}

	public List<Author> getAuthors() {
		return this.books.stream().map(b -> b.getAuthor()).distinct().collect(Collectors.toList());
	}

	public Author getAuthor(Long id) {
		return this.authors.stream().collect(Collectors.toMap(Author::getId, Function.identity())).get(id);
	}

	private List<Book> getBooksInternal() {
		return libraries.stream().flatMap(lib -> lib.getBooks().stream()).distinct().collect(Collectors.toList());
	}

	private List<Author> getAuthorsInternal() {
		return this.books.stream().map(b -> b.getAuthor()).distinct().collect(Collectors.toList());
	}

	private void initLibraries() {
		Author author1 = createAuthor(1l);
		Book b11 = createBook(1l, author1);
		Book b12 = createBook(2l, author1);

		Author author2 = createAuthor(2l);
		Book b21 = createBook(3l, author2);
		Book b22 = createBook(4l, author2);
		Book b23 = createBook(5l, author2);
		Book b24 = createBook(6l, author2);

		Author author3 = createAuthor(3l);
		Book b31 = createBook(7l, author3);

		this.libraries.add(createLibrary(1l, List.of(b11, b12, b21, b22, b23, b24, b31)));
		this.libraries.add(createLibrary(2l, List.of(b22, b31)));
	}

	private Library createLibrary(Long id, List<Book> books) {
		Library lib = new Library();
		lib.setId(id);
		lib.setAdress("Adress " + id);
		lib.setName("Library_" + id);
		lib.setBooks(books);
		return lib;
	}

	private Book createBook(Long id, Author author) {
		Book book = new Book();
		book.setId(id);
		author.getBooks().add(book);
		book.setAuthor(author);
		book.setCategory("Category " + id);
		book.setTitle("Title " + id);
		book.setYear(1900 + random.nextInt(120));
		return book;
	}

	private Author createAuthor(Long id) {
		Author author = new Author();
		author.setId(id);
		author.setName("Author_" + id);
		return author;
	}

}
