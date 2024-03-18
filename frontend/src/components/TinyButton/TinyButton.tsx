interface TinyButtonProps {
  label: string;
  onClick?: () => void;
}

export default function TinyButton({ label, ...props }: TinyButtonProps) {
  return (
    <button
      className={`min-w-fit h-[2.4rem] px-[0.8rem] rounded-[0.8rem]
        text-custom-white custom-light-text bg-custom-purple hover:bg-custom-dark-purple`}
      {...props}
    >
      {label}
    </button>
  );
}
