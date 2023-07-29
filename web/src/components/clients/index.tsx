"use client";
import { Client } from "@/models/client";

interface ClientsProps {
  clients?: Client[];
  handleDelete: (idClient: string) => void;
  handleEdit: (client: Client) => void;
}

export function Clients({ clients, handleDelete, handleEdit }: ClientsProps) {
  return (
    <table border={2} className="border-collapse w-4/5 ">
      <thead>
        <tr>
          <td className="border-2 border-black p-1 h-20 text-center font-bold">
            ID
          </td>
          <td className="border-2 border-black p-1 h-20 text-center font-bold">
            Data Cadastro
          </td>
          <td className="border-2 border-black p-1 h-20 text-center font-bold">
            Nome / Razão Social
          </td>
          <td className="border-2 border-black p-1 h-20 text-center font-bold">
            CPF / CNPJ
          </td>
          <td className="border-2 border-black p-1 h-20 text-center font-bold">
            Ações
          </td>
        </tr>
      </thead>
      <tbody>
        {clients && (clients as Client[])?.map((client) => (
          <tr key={client.id}>
            <td className="border-2 border-black p-1 h-20">{client.id}</td>
            <td className="border-2 border-black p-1 h-20">
              {client.createdAt &&
                Intl.DateTimeFormat("pt-BR").format(new Date(client.createdAt))}
            </td>
            <td className="border-2 border-black p-1 h-20">
              {client.individual?.name || client.legal?.corporateName}
            </td>
            <td className="border-2 border-black p-1 h-20">
              {client.individual?.ir || client.legal?.cnpj}
            </td>
            <td className="border-2 border-black p-1 h-20">
              <div className="flex flex-col justify-between h-full">
                <button
                  className="bg-blue-500 text-white font-bold rounded-lg"
                  onClick={() => handleEdit(client)}
                >
                  Editar
                </button>
                <button
                  className="bg-red-500 text-white font-bold rounded-lg"
                  onClick={() => client.id && handleDelete(client.id)}
                >
                  Excluir
                </button>
              </div>
            </td>
          </tr>
        ))}
      </tbody>
    </table>
  );
}
