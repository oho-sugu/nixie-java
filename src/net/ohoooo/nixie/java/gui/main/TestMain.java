/*
 * Created on 2013/09/29
 *
 * To change the template for this generated file go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
package net.ohoooo.nixie.java.gui.main;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.storage.file.FileRepositoryBuilder;

/**
 * @author Suguru Oho
 *
 * To change the template for this generated type comment go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
public class TestMain {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub
		File dir = new File("testrepo-origin");
		rmRf(dir);
		
		Git.init().setDirectory(dir).call();
		
		Repository repo = FileRepositoryBuilder.create(new File(dir.getAbsolutePath(),".git"));
		
		Git git = new Git(repo);
		
		makeDummyBigFile(dir.getAbsolutePath()+"\\test1.dat", 100);
		
		git.add().addFilepattern("test1.dat").call();
		git.commit().setMessage("Test1 commit").setAll(true).call();
		
	}
	
	private static void makeDummyBigFile(String filename, int size) throws Exception {
		File file = new File(filename);
		BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(file));
		
		for(int i = 0; i < size; i++){
			bos.write((int)(Math.random()*1024)&0x000000ff);
		}
		bos.close();
		
	}
	
	private static void rmRf(File dir) throws Exception {
		if( dir.exists()==false ){
			return ;
		}
	
		if(dir.isFile()){
			dir.delete();
		}
			
		if(dir.isDirectory()){
			File[] files=dir.listFiles();
			for(int i=0; i<files.length; i++){
				rmRf( files[i] );
			}
			dir.delete();
		}
	}

}
