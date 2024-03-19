interface MoneyShowProps {
  mode: 'DIVIDED' | 'DIVIDED_GRAY' | 'UNDIVIDED';
  text: string[];
  money: string[];
  unit: string;
  moneyColor?: string[];
}

export default function MoneyShow({
  mode,
  text,
  money,
  unit,
  moneyColor,
}: MoneyShowProps) {
  let backgroundColor: string;
  let textColor: string;

  switch (mode) {
    case 'DIVIDED':
      backgroundColor = 'bg-custom-light-purple';
      textColor = 'text-custom-white';
      break;
    case 'DIVIDED_GRAY':
      backgroundColor = 'bg-custom-light-gray';
      textColor = 'text-custom-dark-gray';
      break;
    case 'UNDIVIDED':
    default:
      backgroundColor = 'bg-custom-purple';
      textColor = 'text-custom-white';
      break;
  }

  const infoText = (index: number) => (
    <div className="flex justify-between grow">
      <p>{text[index]}</p>
      <div className="flex">
        <p className={moneyColor && moneyColor[index]}>{money[index]}</p>
        <p>{unit}</p>
      </div>
    </div>
  );

  return (
    <div
      className={`w-full h-[3.6rem] px-8 rounded-[2rem] custom-medium-text ${textColor} ${backgroundColor}
        flex justify-between items-center`}
    >
      {infoText(0)}
      {(mode === 'DIVIDED' || mode === 'DIVIDED_GRAY') && (
        <>
          <div className="mx-4">|</div>
          {infoText(1)}
        </>
      )}
    </div>
  );
}
