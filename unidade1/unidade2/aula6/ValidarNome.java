ValidarNomete String validarNome(String nome) {
    if (nome == null) {
        System.err.println("nome nulo");
        return "SemNome";
    }

    String normalizado = nome.trim();

    if (normalizado.isEmpty()) {
        System.err.println("nome vazio");
        return "SemNome";
    }

    if (normalizado.length() < 3) {
        System.err.println("nome muito curto");
        return "SemNome";
    }

    if (!normalizado.matches("[\\p{L} ]+")) {
        System.err.println("nome contém caracteres inválidos");
        return "SemNome";
    }

    return normalizado;
}