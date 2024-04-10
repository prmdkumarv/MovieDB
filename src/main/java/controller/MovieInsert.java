package controller;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import dao.MovieDao;
import dto.Movie;

@WebServlet("/insert_movie")
@MultipartConfig
public class MovieInsert extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("insert.html").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String name=req.getParameter("mname");
		String language=req.getParameter("language");
		
		Part pic =req.getPart("mimage");
		String genre=req.getParameter("mgenre");
		try {
			double rating=Double.parseDouble(req.getParameter("mrating"));
//			resp.getWriter().print(name+" "+language+" "+rating+" "+pic+" "+genre);
			
			Movie movie = new Movie();
			movie.setLanguage(language);
			movie.setGenre(genre);
			movie.setName(name);
			movie.setRating(rating);
			
			byte[] image = new byte[pic.getInputStream().available()];
			pic.getInputStream().read(image);
			movie.setPicture(image);
			
			MovieDao dao = new MovieDao();
			dao.saveMovie(movie);
			
			resp.getWriter().print("<h1 align='center'>Movie added succesfully</h1>");
			req.getRequestDispatcher("insert.html").include(req, resp);
			
		}
		
		catch (NumberFormatException e) {
			resp.getWriter().print("<h1 align='center'>Enter proper rating</h1>");
			req.getRequestDispatcher("insert.html").include(req, resp);
		}
		
	}
}
