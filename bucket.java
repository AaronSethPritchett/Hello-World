
class bucket
	{
		private node n;
		public boolean get(String input)
		{
			node nextNode = n;
			while(nextNode != null)
			{
				if (nextNode.word.equals(input))
				{
					return true;
				}
				nextNode = nextNode.next;
			}
			return false;
		}
		
		public void placeWord(String in)
		{
			for (node current = n; current != null; current = current.next)
			{
				if (in.equals(current.word))
					return;
			}
			n = new node (in, n);
		}
		class node
		{
			String word;
			node next;
			
			public node(String in, node next)
			{
				this.word = in;
				this.next = next;
			}
		}
	}