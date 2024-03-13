import type { Meta, StoryObj } from '@storybook/react';

import Input from './Input';

const meta = {
  title: 'Common/Input',
  component: Input,
  parameters: {},
  tags: ['autodocs'],
  argTypes: {
    reset: {
      description:
        'input의 내용을 모두 지웁니다, 없애려면 false로 설정하면 됩니다.',
    },
    value: {
      description:
        'inputValue를 지정해줄 수 있습니다.',
    },
    setValue: {
      description:
        'value에 해당하는 setValue 함수를 줄 수 있습니다.',
    },
    placeholder: {
      description:
        'placeholder를 지정해줄 수 있습니다.',
    }
  },
} satisfies Meta<typeof Input>;

export default meta;
type Story = StoryObj<typeof meta>;

export const Main: Story = {
  args: {
    placeholder: '내용을 입력해주세요'
  },
};
