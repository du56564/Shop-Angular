package com.shop.resource;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.shop.domain.Item;
import com.shop.service.ItemService;
@RestController
@RequestMapping("/item")
public class ItemResource {
	
	@Autowired
	private ItemService itemService;
	
	@RequestMapping (value="/add", method=RequestMethod.POST)
	public Item addItemPost(@RequestBody Item item) {
		return itemService.save(item);
	}
	
	
	@RequestMapping(value="/add/image", method=RequestMethod.POST)
	public ResponseEntity<String> upload(@RequestParam("id") Long id, HttpServletResponse response, HttpServletRequest request){
		try {
			Item item = itemService.findOne(id);
			MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest)request;
			Iterator<String> itr = multipartHttpServletRequest.getFileNames();
			MultipartFile multipartFile = multipartHttpServletRequest.getFile(itr.next());
			String fileName = id+".png";
			
			byte[] bytes = multipartFile.getBytes();
			BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File("src/main/resources/static/image/item/"+fileName)));
			stream.write(bytes);
			stream.close();
			
			return new ResponseEntity<String>("Upload Success!", HttpStatus.OK);
		}catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>("Upload Failed!", HttpStatus.BAD_REQUEST);
		}
	}
	
	@RequestMapping(value="/update/image", method=RequestMethod.POST)
	public ResponseEntity<String> updateImagePost(
			@RequestParam("id") Long id,
			HttpServletResponse response, HttpServletRequest request
			){
		try {
			Item item = itemService.findOne(id);
			MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
			Iterator<String> it = multipartRequest.getFileNames();
			MultipartFile multipartFile = multipartRequest.getFile(it.next());
			String fileName = id+".png";
			try {
				Files.delete(Paths.get("src/main/resources/static/image/item/"+fileName));
				
			}catch(Exception e){
				System.out.println("Exception in deleting files.");
			}
			
			byte[] bytes = multipartFile.getBytes();
			BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File("src/main/resources/static/image/item/"+fileName)));
			stream.write(bytes);
			stream.close();
			
			return new ResponseEntity<String>("Upload Success!", HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>("Upload failed!", HttpStatus.BAD_REQUEST);
		}
	}

	
	@RequestMapping("/itemList")
	public List<Item> getItemList(){
		return itemService.findAll();
	} 
	
	@RequestMapping(value="/update", method=RequestMethod.POST)
	public Item updateItemPost(@RequestBody Item item){
		return itemService.save(item);
	}
	
	@RequestMapping("/{id}")
	public Item getItem(@PathVariable("id") Long id){
		Item book = itemService.findOne(id);
		return book;
	}
	
	@RequestMapping(value="/remove", method=RequestMethod.POST)
	public ResponseEntity<String> remove(
			@RequestBody String id
			) throws IOException {
		itemService.removeOne(Long.parseLong(id));
		String fileName = id+".png";
		
		try {	
			Files.delete(Paths.get("src/main/resources/static/image/item/"+fileName));
			
		}catch(Exception e){
			System.out.println("Exception in deleting files.");
		}		
		return new ResponseEntity<>("Remove Success!", HttpStatus.OK);
	}

	
}
