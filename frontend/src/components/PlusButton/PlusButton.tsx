import { PlusIcon } from '@heroicons/react/24/solid';

interface PlusButtonProps {
  label: string;
  onClick?: () => void;
}

export default function PlusButton({ label, ...props }: PlusButtonProps) {
  return (
    <button
      className={`w-full h-[6.4rem] px-[2rem] rounded-[2rem]
      custom-semibold-text text-custom-white bg-custom-purple hover:bg-custom-dark-purple
      flex justify-start items-center gap-[1.2rem]`}
      {...props}
    >
      <PlusIcon className="size-[2rem] fill-custom-white" />
      {label}
    </button>
  );
}
