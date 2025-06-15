import { ArrowDown } from "lucide-react";

export const Welcome = () => {
    return (
        <section id="welcome" className="relative min-h-screen flex flex-col items-center justify-center px-4">
            <div className="container max-w-4xl mx-auto text-center z-10">
                <div className="space-y-6">
                    <h1 className="text-4xl md:text-6xl font-bold">

                        <span className="block mb-8 opacity-0 animate-fade-in-delay-1 transition-transform
                        duration-300 ease-in-out hover:scale-130 hover:-translate-y-1">Plan events.<br/></span>

                        <span className="block mb-8 opacity-0 animate-fade-in-delay-2 transition-transform
                        duration-300 ease-in-out hover:scale-130 hover:-translate-y-1">Collect feedback.<br/></span>

                        <span className="block mb-8 opacity-0 animate-fade-in-delay-3 transition-transform
                        duration-300 ease-in-out hover:scale-130 hover:-translate-y-1">Analyze feedback.<br/></span>

                        <span className="block mb-8 text-primary opacity-0 animate-fade-in-delay-4 transition-transform
                        duration-300 ease-in-out hover:scale-130 hover:-translate-y-1">Event<span className="text-welcome">Sync</span>.</span>
                    </h1>
                </div>
            </div>

      <div className="absolute bottom-8 left-1/2 transform -translate-x-1/2 flex flex-col items-center animate-bounce">
        <span className="text-xl text-muted-foreground mb-2 font-bold"> Scroll </span>
        <ArrowDown className="h-5 w-5 text-primary" />
      </div>
        </section>
    );
};