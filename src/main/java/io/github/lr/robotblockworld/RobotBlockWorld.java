package io.github.lr.robotblockworld;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.stream.IntStream;

import io.github.lr.robotblockworld.entities.Block;

public class RobotBlockWorld {

	private static final String SPACE_REGEXP = "\\s+";
	private static final String MOVE_REGEXP = "^move \\d+ to \\d+$";
	
	private static final Pattern MOVE_PATTERN = Pattern.compile(MOVE_REGEXP);
	
	private static final String QUIT_COMMAND = "quit";
	
	private static final String EMPTY = "";
	
	@SuppressWarnings("resource")
	public static void main(String[] args) {
		int count = 0;
		
		try {
			count = Integer.parseInt(args[0]);
		
		} catch (IndexOutOfBoundsException | NumberFormatException e) {
			
			System.err.println("Invalid Number Argument");
			
			System.exit(1);
		}
		
		List<Block> list = new ArrayList<>();
		
		IntStream.range(0, count).forEachOrdered(i -> list.add(new Block(i + 1)));
		
		Scanner scanner = new Scanner(System.in);
		
		String commandLine = scanner.nextLine();
		
		while (!QUIT_COMMAND.equals(commandLine)) {
			
			if (MOVE_PATTERN.matcher(commandLine).matches()) {
				
				String[] commandArguments = commandLine.split(SPACE_REGEXP);
				
				int from = Integer.parseInt(commandArguments[1]);
				int to = Integer.parseInt(commandArguments[3]);
				
				if (from > 0 && to > 0 && from <= count && to <= count && from != to) {
				
					Block blockFrom = list.get(from - 1);
					Block blockTo = list.get(to - 1);
					
					if (blockTo.isLeaf() && (blockFrom.isLeaf() || !blockTo.sameRow(blockFrom))) {
						blockFrom.cleanFather();
						if (!blockFrom.isLeaf()) {
							blockFrom.clean();
						}
						blockTo.setSon(blockFrom);
						blockFrom.setFather(blockTo);
					}

				}
				
			}
			
			commandLine = scanner.nextLine();
		}

		list.stream().forEachOrdered(block -> System.out.println(block.isRoot() ? block.toString() : EMPTY));
	}
	
}
