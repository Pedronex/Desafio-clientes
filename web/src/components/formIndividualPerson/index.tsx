import { useForm } from "react-hook-form";
import ReactInputMask from "react-input-mask";

import { Client } from "@/models/client";

export type FormTypeIndividual = {
  birthday: Date;
  ir: string;
  name: string;
  phone: string;
};

interface FormIndividualPersonProps {
  handleCreateIndividual: (data: FormTypeIndividual) => Promise<void>;
  individualPerson?: Client;
}

export function FormIndividualPerson({
  handleCreateIndividual,
  individualPerson,
}: FormIndividualPersonProps) {
  const { register, handleSubmit } = useForm<FormTypeIndividual>();

  return (
    <form
      onSubmit={handleSubmit(handleCreateIndividual)}
      className="flex flex-row w-full flex-wrap justify-between"
    >
      <div className="w-full">
        <label htmlFor="name" className="block text-sm font-medium leading-6">
          Nome
        </label>
        <div className="my-2">
          <input
            {...register("name")}
            type="text"
            defaultValue={individualPerson?.individual?.name}
            className="block w-full rounded-md border-0 py-1.5 shadow-sm ring-1 ring-inset ring-white-300 focus:ring-2 focus:ring-inset focus:ring-blue-600 sm:text-sm sm:leading-6 p-2"
          />
        </div>
      </div>

      <div className="w-full">
        <label htmlFor="name" className="block text-sm font-medium leading-6">
          Data de Nascimento
        </label>
        <div className="my-2">
          <input
            {...register("birthday")}
            type="date"
            defaultValue={individualPerson?.individual?.birthday?.toString().split('T')[0] || '2023-01-01'}
            className="block w-full rounded-md border-0 py-1.5 shadow-sm ring-1 ring-inset ring-white-300 focus:ring-2 focus:ring-inset focus:ring-blue-600 sm:text-sm sm:leading-6 p-2"
          />
        </div>
      </div>

      <div>
        <label htmlFor="name" className="block text-sm font-medium leading-6">
          CPF
        </label>
        <div className="my-2">
          <ReactInputMask
            mask={"999.999.999-99"}
            maskChar=""
            {...register("ir")}
            type="text"
            defaultValue={individualPerson?.individual?.ir}
            disabled={individualPerson?.individual?.ir != undefined}
            required
            className="block w-full rounded-md border-0 py-1.5 shadow-sm ring-1 ring-inset ring-white-300 focus:ring-2 focus:ring-inset focus:ring-blue-600 sm:text-sm sm:leading-6 p-2"
          />
        </div>
      </div>
      <div>
        <label htmlFor="name" className="block text-sm font-medium leading-6">
          Telefone
        </label>
        <div className="my-2">
          <ReactInputMask
            mask={"(99) 9999-9999"}
            maskChar=""
            {...register("phone")}
            type="text"
            defaultValue={individualPerson?.phone}
            className="block w-full rounded-md border-0 py-1.5 shadow-sm ring-1 ring-inset ring-white-300 focus:ring-2 focus:ring-inset focus:ring-blue-600 sm:text-sm sm:leading-6 p-2"
          />
        </div>
      </div>
      <button className="bg-blue-500 text-white p-1 font-bold rounded">
        Salvar
      </button>
    </form>
  );
}
