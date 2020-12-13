package com.nasri.springbootgraphql.resolver;

import java.util.List;

import org.springframework.stereotype.Service;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.nasri.springbootgraphql.dao.LibraryDao;
import com.nasri.springbootgraphql.dto.Author;
import com.nasri.springbootgraphql.dto.Book;
import com.nasri.springbootgraphql.dto.Library;

@Service
public class QueryResolver implements GraphQLQueryResolver {

	private final LibraryDao libraryDao;

	public QueryResolver(LibraryDao libraryDao) {
		this.libraryDao = libraryDao;
	}

	public List<Book> recentBooks(int count) {
		return libraryDao.recentBooks(count);
	}

	public List<Author> getAuthors() {
		return libraryDao.getAuthors();
	}

	public Author getAuthor(Long id) {
		return libraryDao.getAuthor(id);
	}
	
	public List<Library> getLibraries(int count){
		return libraryDao.getLibraries(count);
	}
	
	public Library getLibrary(Long id) {
		return libraryDao.getLibrary(id);
	}

}
