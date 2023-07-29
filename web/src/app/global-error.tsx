"use client";
import { useRouter } from "next/navigation";

export default function Error({
    error,
    reset
}: {
    error: Error,
    reset: () => void
}) {
  const { back } = useRouter();

  return (
    <div className="flex items-center justify-center min-h-screen flex-col">
      <h1 className="text-xl2 font-bold mb-2">{error.name}</h1>
      <h2 className="text-xl font-bold mb-2">Erro desconhecido</h2>
      <p>Contate o administrador do sistema</p>
      <button
        className="text-sm bg-[#2773b4] p-2 rounded-md"
        onClick={() => back()}
      >
        Voltar para a Página anterior
      </button>
    </div>
  );
}
