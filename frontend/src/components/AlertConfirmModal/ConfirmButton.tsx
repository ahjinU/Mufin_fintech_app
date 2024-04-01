interface ButtonProps {
  mode: 'OKAY' | 'CANCEL';
  label: string;
  onClick?: () => void;
}

export default function ConfimButton({ mode, label, ...props }: ButtonProps) {
  let backgroundColor: string;
  switch (mode) {
    case 'OKAY':
      backgroundColor = 'bg-custom-purple';
      break;
    case 'CANCEL':
      backgroundColor = 'bg-custom-light-purple';
      break;
    default:
      backgroundColor = 'bg-custom-medium-gray';
  }

  return (
    <button
      className={`w-[9.0rem] h-[3.2rem] rounded-[0.8rem] text-custom-white custom-semibold-text ${backgroundColor}`}
      {...props}
    >
      {label}
    </button>
  );
}
