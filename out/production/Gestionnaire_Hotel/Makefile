# Makefile pour compiler notre projet de Gestion d'hotel

# Compiler
JAVAC = javac

# Java source files
SRCDIR = src
SOURCES = $(wildcard $(SRCDIR)/*.java)

# Class files
BINDIR = bin
CLASSES = $(SOURCES:$(SRCDIR)/%.java=$(BINDIR)/%.class)

# Main class
MAINCLASS = Main

# Executer le programme
run: all
	java -cp $(BINDIR) $(MAINCLASS)

# Compile
all: $(CLASSES)

$(BINDIR)/%.class: $(SRCDIR)/%.java
	@mkdir -p $(BINDIR)
	$(JAVAC) -d $(BINDIR) $<

# Clean
clean:
	rm -rf $(BINDIR)

.PHONY: run all clean
