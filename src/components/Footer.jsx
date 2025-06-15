import { ArrowUp } from "lucide-react";

export const Footer = () => {
    return (
        <footer className="py-12 px-4 bg-card relative border-t border-border mt-12 pt-8 flex items-center">
        <p className="flex-grow text-center text-m text-muted-foreground opacity-40">
            &copy; {new Date().getFullYear()}
        </p>
        <a
            href="#welcome"
            className="p-2 rounded-full bg-primary/10 hover:bg-primary/20 text-primary transition-colors"
        >
            <ArrowUp size={30} />
        </a>
        </footer>
    );
};