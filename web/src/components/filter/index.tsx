"use client";

import { useForm } from "react-hook-form";
import { InputMaskCpfOrCnpj } from "../Inputmask";
import { useRouter } from "next/navigation";
import { Client } from "@/models/client";
import { validCNPJ, validCPF } from "@/utils/verifyIdentify";

type FormType = {
  createdAt: string;
  name: string;
  id: string;
  typePerson: "both" | "individual" | "legal";
};

interface FilterProps {
  listClients: Client[];
  setListClients: (clients: Client[]) => void;
}

export function Filter({ listClients, setListClients }: FilterProps) {
  const { register, handleSubmit } = useForm<FormType>();
  const navigation = useRouter();

  const FORMAT_CPF = 14;
  const FORMAT_CNPJ = 18;

  async function handleFilter(data: FormType) {
    let filteredList = listClients.filter((client) =>
      data.typePerson != "both" ? client[data.typePerson] != null : true
    );

    filteredList = filteredList.filter(
      (client) =>
        client.individual?.name
          .toLowerCase()
          .includes(data.name.toLowerCase()) ||
        client.legal?.corporateName
          .toLocaleLowerCase()
          .includes(data.name.toLowerCase())
    );

    filteredList = filteredList.filter(
      (client) => client.createdAt && client.createdAt.includes(data.createdAt)
    );

    if (!validCNPJ(data.id) && data.id.length === FORMAT_CNPJ) {
      alert("CNJP Inválido");
    } else if (!validCPF(data.id) && data.id.length === FORMAT_CPF) {
      alert("CPF Inválido");
    } else if (
      data.id.length != 0 &&
      data.id.length !== FORMAT_CPF &&
      data.id.length !== FORMAT_CNPJ
    ) {
      alert("Preencha o campo corretamente para validação do CPF / CNPJ");
    } else {
      filteredList = filteredList.filter(
        (client) =>
          client.individual?.ir.includes(data.id) ||
          client.legal?.cnpj.includes(data.id)
      );
    }

    setListClients(filteredList);
  }

  function handleCreateClient() {
    navigation.push("/create");
  }

  return (
    <div className="flex border-2 rounded p-2 flex-col w-3/4">
      <span className="top-0 left-0 p-1 bg-blue-500 text-white w-fit rounded">
        Filtro
      </span>
      <div className="flex flex-row w-full">
        <div className="flex flex-row mr-2">
          <input
            {...register("typePerson")}
            type="radio"
            value={"individual"}
            className="block rounded-md ring-inset ring-white-300 focus:ring-2 focus:ring-inset focus:ring-blue-600 sm:text-sm sm:leading-6 p-2"
          />
          Física
          <input
            {...register("typePerson")}
            type="radio"
            value={"legal"}
            className="block ml-1 rounded-md ring-inset ring-white-300 focus:ring-2 focus:ring-inset focus:ring-blue-600 sm:text-sm sm:leading-6 p-2"
          />
          Juridica
          <input
            {...register("typePerson")}
            type="radio"
            value={"both"}
            className="block ml-1 rounded-md ring-inset ring-white-300 focus:ring-2 focus:ring-inset focus:ring-blue-600 sm:text-sm sm:leading-6 p-2"
            checked
          />
          Ambos
        </div>
      </div>
      <form
        onSubmit={handleSubmit(handleFilter)}
        className="flex flex-row w-full justify-between"
      >
        <div>
          <label htmlFor="name" className="block text-sm font-medium leading-6">
            Data Cadastro
          </label>
          <div className="my-2">
            <input
              {...register("createdAt")}
              type="date"
              className="block w-full rounded-md border-0 py-1.5 shadow-sm ring-1 ring-inset ring-white-300 focus:ring-2 focus:ring-inset focus:ring-blue-600 sm:text-sm sm:leading-6 p-2"
            />
          </div>
        </div>
        <div>
          <label htmlFor="name" className="block text-sm font-medium leading-6">
            Nome / Razão Social
          </label>
          <div className="my-2">
            <input
              {...register("name")}
              type="text"
              className="block w-full rounded-md border-0 py-1.5 shadow-sm ring-1 ring-inset ring-white-300 focus:ring-2 focus:ring-inset focus:ring-blue-600 sm:text-sm sm:leading-6 p-2"
            />
          </div>
        </div>
        <div>
          <label htmlFor="name" className="block text-sm font-medium leading-6">
            CPF / CNPJ
          </label>
          <div className="my-2">
            <InputMaskCpfOrCnpj register={register} name="id" />
          </div>
        </div>
        <div className="flex flex-col min-h-max justify-between">
          <button className="bg-blue-500 text-white p-1 font-bold rounded">
            Filtrar
          </button>
          <button
            onClick={() => handleCreateClient()}
            className="bg-blue-500 text-white p-1 font-bold rounded"
          >
            Incluir
          </button>
        </div>
      </form>
    </div>
  );
}
