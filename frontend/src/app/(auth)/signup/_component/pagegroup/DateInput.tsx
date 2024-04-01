import { Input } from '@/components';

export default function DateInput({
  name,
  onChange,
}: {
  name: 'year' | 'month' | 'day';
  onChange: (e: { target: { name: string; value: string } }) => void;
}) {
  return (
    <div className="flex items-center gap-[0.4rem]">
      <Input
        isNumber={true}
        placeholder={name === 'year' ? '1998' : name === 'month' ? '04' : '22'}
        name={name}
        onChange={(e) => onChange(e)}
        width={name === 'year' ? 'w-[10.4rem]' : 'w-[6.4rem]'}
        reset={false}
      />
      <p className="custom-semibold-text text-custom-black">
        {name === 'year' ? '년' : name === 'month' ? '월' : '일'}
      </p>
    </div>
  );
}
