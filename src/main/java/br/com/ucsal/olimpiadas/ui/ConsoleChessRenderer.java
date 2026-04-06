package br.com.ucsal.olimpiadas.ui;

public class ConsoleChessRenderer implements TabuleiroRenderer {
    @Override
    public void renderizar(String fen) {
        if (fen == null) return;
        String parteTabuleiro = fen.split(" ")[0];
        String[] ranks = parteTabuleiro.split("/");

        System.out.println("\n    a b c d e f g h\n   -----------------");
        for (int r = 0; r < 8; r++) {
            System.out.print((8 - r) + " | ");
            for (char c : ranks[r].toCharArray()) {
                if (Character.isDigit(c)) {
                    int vazios = c - '0';
                    for (int i = 0; i < vazios; i++) System.out.print(". ");
                } else {
                    System.out.print(c + " ");
                }
            }
            System.out.println("| " + (8 - r));
        }
        System.out.println("   -----------------\n    a b c d e f g h\n");
    }
}