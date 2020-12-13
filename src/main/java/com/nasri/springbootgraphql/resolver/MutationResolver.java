package com.nasri.springbootgraphql.resolver;

import java.util.List;

import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.nasri.springbootgraphql.dto.Book;

@Service
public class MutationResolver implements GraphQLMutationResolver {

	public Book addBook(@NonNull String title, int year, String category, @NonNull String author,
			@NonNull List<String> libraries) {
		return new Book();
	}

}
