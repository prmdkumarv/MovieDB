package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.MovieDao;
import dto.Movie;

@WebServlet("/fetch-movies")
public class FetchMovies extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		MovieDao dao = new MovieDao();
		List<Movie> list = dao.fetchMovies();
		
		if(list.isEmpty()) {
			
			resp.getWriter().print("<h1 align='center' style='color:red'>No movies added</h1>");
			req.getRequestDispatcher("home.html").include(req, resp);
			
		}else {
			
			resp.getWriter().print("<html><body><div align='center>'<table border='1'>");
			
			resp.getWriter().print("<tr>");
			resp.getWriter().print("<th>Name</th>");
			resp.getWriter().print("<th>Image</th>");
			resp.getWriter().print("<th>Language</th>");
			resp.getWriter().print("<th>Genre</th>");
			resp.getWriter().print("<th>Rating</th>");
			resp.getWriter().print("<th>Edit</th>");
			resp.getWriter().print("<th>Delte</th>");
			resp.getWriter().print("</tr>");
			
			for(Movie movie:list) {
			resp.getWriter().print("<tr>");
			resp.getWriter().print("<th>"+movie.getName()+"</th>");
			resp.getWriter().print("<th>Image</th>");
			resp.getWriter().print("<th>"+movie.getLanguage()+"</th>");
			resp.getWriter().print("<th>"+movie.getGenre()+"</th>");
			resp.getWriter().print("<th>"+movie.getRating()+"</th>");
			resp.getWriter().print("<th>Edit</th>");
			resp.getWriter().print("<th>Delte</th>");
			resp.getWriter().print("</tr>");
			}
			
			resp.getWriter().print("<button>BACK</button>");
			resp.getWriter().print("</div></table></body></html>");
			
		}
	}
}
